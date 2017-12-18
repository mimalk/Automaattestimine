package repositorytest;

import mock.MockJsonReader;
import org.junit.Before;
import org.junit.Test;
import weatherrepository.WeatherRepository;
import validator.DataValidator;
import weather.CurrentWeather;
import weather.WeatherReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Current weather test.
 */
public class CurrentWeatherTest {
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
        when(mockWeatherReader.readJsonFromUrl("weather")).thenReturn(mockJsonReader.getJson("weather"));
        when(mockWeatherReader.getMeasurementUnit()).thenReturn("Celsius");
        when(mockWeatherReader.getCityName()).thenReturn("Tallinn");
        weatherRepositoryMock = new WeatherRepository(mockWeatherReader);
    }
    @Test
    public void testGetCurrentTemperatureMock() throws Exception {
        CurrentWeather currentWeather = weatherRepositoryMock.getCurrentWeather();
        assertEquals(1.0, currentWeather.getCurrentTemperature(), 0.0001);
    }
    @Test
    public void testGetCurrentWeatherCoordinatesMock() throws Exception {
        CurrentWeather currentWeather = weatherRepositoryMock.getCurrentWeather();
        assertEquals("{\"lon\":24.75,\"lat\":59.44}", currentWeather.getCoordinates().toString());
    }
    @Test
    public void testGetCurrentWeatherCityMock() throws Exception {
        CurrentWeather currentWeather = weatherRepositoryMock.getCurrentWeather();
        assertEquals("Tallinn", currentWeather.getCityName());
    }
    @Test
    public void testIfCurrentTemperatureIsValid(){
        try {
            CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
            dataValidator.validateTemperature(currentWeather.getCurrentTemperature(), weatherReader.getMeasurementUnit());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testIfCurrentWeatherCoordinatesAreValid() {
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
