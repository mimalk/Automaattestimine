package utilitytest;


import org.junit.Before;
import org.junit.Test;
import validator.DataValidator;
import weather.Coordinates;

public class ValidatorTest {
    private DataValidator dataValidator;
    @Before
    public void setUp() {
        dataValidator = new DataValidator();
    }
    @Test
    public void testValidCoordinates() throws Exception {
        dataValidator.validateCoordinates(new Coordinates("{\"lon\":20,\"lat\":59.437}"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testLongitudeNotValid() throws Exception {
        dataValidator.validateCoordinates(new Coordinates("{\"lon\":200,\"lat\":59.437}"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testLatitudeNotValid() throws Exception {
        dataValidator.validateCoordinates(new Coordinates("{\"lon\":10,\"lat\":100}"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTemperatureCelsius() throws Exception {
        dataValidator.validateTemperature(90.0, "Celsius");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTemperatureFahrenheit() throws Exception {
        dataValidator.validateTemperature(-100.0, "Fahrenheit");
    }
    @Test
    public void testValidTemperature() throws Exception {
        dataValidator.validateTemperature(2.0, "Celsius");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMeasurementUnit() throws Exception {
        dataValidator.validateTemperature(5.0, "Clsss");
    }
}
