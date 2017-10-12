package utilitytest;

import utility.Converter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for converter.
 */
public class ConverterTest {
    @Test
    public void testConvertKelvinsToCelsius() {
        try {
            Converter converter = new Converter();
            assertEquals(6.85, converter.convertKelvins(280.0, "Celsius"),0.001);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testConvertKelvinsToFahrenheit() {
        try {
            Converter converter = new Converter();
            assertEquals(44.33, converter.convertKelvins(280.0, "Fahrenheit"),0.001);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
