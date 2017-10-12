package repositorytest;

import org.junit.Before;
import org.junit.Test;
import weatherrepository.WeatherRepository;
import validator.DataValidator;
import weatherproject.DailyWeather;
import weatherproject.ThreeDayForecast;
import weatherproject.WeatherReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Forecast test.
 */
public class ThreeDayForecastTest {
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
    public void testForecastCoordinates() {
        try {
            ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
            dataValidator.validateCoordinates(threeDayForecast.getCoordinates());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testForecastDaysAmount() {
        try {
            ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
            assertEquals(3, threeDayForecast.getThreeDayWeathers().size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testForecastHighestTemperature() {
        try {
            ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
            for (DailyWeather dailyWeather : threeDayForecast.getThreeDayWeathers()) {
                dataValidator.validateTemperature(dailyWeather.getHighestTemperature(), weatherReader.getMeasurementUnit());
            }
        } catch (Exception exe) {
            fail(exe.getMessage());
        }

    }
    @Test
    public void testForecastLowestTemperature() {
        try {
            ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
            for (DailyWeather dailyWeather : threeDayForecast.getThreeDayWeathers()) {
                dataValidator.validateTemperature(dailyWeather.getLowestTemperature(), weatherReader.getMeasurementUnit());
            }
        } catch (Exception exe) {
            fail(exe.getMessage());
        }
    }

    @Test
    public void testForecastCityEqualsRequestCity() {
        try {
            ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
            assertEquals(threeDayForecast.getCityName(), weatherReader.getCityName());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
