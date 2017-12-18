package weatherreadertest;

import org.junit.Test;
import weather.WeatherReader;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

/**
 * WeatherReader test.
 */
public class WeatherReaderTest {
    @Test
    public void testSettingCityName() {
        WeatherReader weatherReader = new WeatherReader("Tallinn", "Celsius");
        weatherReader.setCity("Tartu");
        assertEquals("Tartu", weatherReader.getCityName());
    }
    @Test(expected = FileNotFoundException.class)
    public void testWeatherReaderWithIncorrectCityName() throws Exception {
        WeatherReader weatherReader = new WeatherReader("yyyyt", "Celsius");
        weatherReader.readJsonFromUrl("weather");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWeatherReaderWithIncorrectRequest() throws Exception {
        WeatherReader weatherReader = new WeatherReader("Tallinn", "Celsius");
        weatherReader.readJsonFromUrl("wthr");
    }
}
