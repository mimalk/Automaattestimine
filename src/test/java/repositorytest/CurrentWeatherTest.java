package repositorytest;

import org.junit.Before;
import org.junit.Test;
import weatherrepository.WeatherRepository;
import validator.DataValidator;
import weather.CurrentWeather;
import weather.WeatherReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Current weather test.
 */
public class CurrentWeatherTest {
    private WeatherReader weatherReader;
    private WeatherRepository weatherRepository;
    private DataValidator dataValidator;
    @Before
    public void setVariables() {
        this.weatherReader = new WeatherReader("Tallinn", "Celsius");
        this.weatherRepository = new WeatherRepository(weatherReader);
        this.dataValidator = new DataValidator();
    }
    @Test
    public void testCurrentTemperature(){
        try {
            CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
            dataValidator.validateTemperature(currentWeather.getCurrentTemperature(), weatherReader.getMeasurementUnit());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testCurrentWeatherCoordinates() {
        try {
            CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
            dataValidator.validateCoordinates(currentWeather.getCoordinates());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testCurrentWeatherCityEqualsRequestCity() {
        try {
            CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
            assertEquals(currentWeather.getCityName(), weatherReader.getCityName());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
