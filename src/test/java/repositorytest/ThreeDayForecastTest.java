package repositorytest;

import mock.MockJsonReader;
import org.junit.Before;
import org.junit.Test;
import weatherrepository.WeatherRepository;
import validator.DataValidator;
import weather.DailyWeather;
import weather.ThreeDayForecast;
import weather.WeatherReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Forecast test.
 */
public class ThreeDayForecastTest {
    private WeatherReader weatherReader;
    private WeatherRepository weatherRepository;
    private DataValidator dataValidator;
    private WeatherRepository weatherRepositoryMock;
    @Before
    public void setVariables() throws Exception {
        this.weatherReader = new WeatherReader("Tallinn", "Celsius");
        this.weatherRepository = new WeatherRepository(weatherReader);
        this.dataValidator = new DataValidator();

        WeatherReader mockWeatherReader = mock(WeatherReader.class);
        MockJsonReader mockJsonReader = new MockJsonReader();
        when(mockWeatherReader.readJsonFromUrl("forecast")).thenReturn(mockJsonReader.getJson("forecast"));
        when(mockWeatherReader.getMeasurementUnit()).thenReturn("Celsius");
        when(mockWeatherReader.getCityName()).thenReturn("Tallinn");
        weatherRepositoryMock = new WeatherRepository(mockWeatherReader);
    }
    @Test
    public void testGetForecastCoordinatesMock() throws Exception {
        ThreeDayForecast threeDayForecast = weatherRepositoryMock.getThreeDayForecast();
        assertEquals("{\"lon\":24.7535,\"lat\":59.437}", threeDayForecast.getCoordinates().toString());
    }
    @Test
    public void testForecastMaxTempMock() throws Exception {
        ThreeDayForecast threeDayForecast = weatherRepositoryMock.getThreeDayForecast();
        DailyWeather day1 = threeDayForecast.getThreeDayWeathers().get(0);
        DailyWeather day2 = threeDayForecast.getThreeDayWeathers().get(1);
        DailyWeather day3 = threeDayForecast.getThreeDayWeathers().get(2);
        assertEquals(2.03, day1.getHighestTemperature(), 0.001);
        assertEquals(1.62, day2.getHighestTemperature(), 0.001);
        assertEquals(-0.33, day3.getHighestTemperature(), 0.001);
    }
    @Test
    public void testForecastCityMock() throws Exception {
        ThreeDayForecast threeDayForecast = weatherRepositoryMock.getThreeDayForecast();
        assertEquals("Tallinn", threeDayForecast.getCityName());
    }
    @Test
    public void testForecastMinTempMock() throws Exception {
        ThreeDayForecast threeDayForecast = weatherRepositoryMock.getThreeDayForecast();
        DailyWeather day1 = threeDayForecast.getThreeDayWeathers().get(0);
        DailyWeather day2 = threeDayForecast.getThreeDayWeathers().get(1);
        DailyWeather day3 = threeDayForecast.getThreeDayWeathers().get(2);
        assertEquals(-0.73, day1.getLowestTemperature(), 0.001);
        assertEquals(-3.93, day2.getLowestTemperature(), 0.001);
        assertEquals(-4.02, day3.getLowestTemperature(), 0.001);
    }
    @Test
    public void testIfForecastCoordinatesAreValid() {
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
    public void testIfForecastHighestTemperaturesAreValid() {
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
    public void testIfForecastLowestTemperaturesAreValid() {
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
