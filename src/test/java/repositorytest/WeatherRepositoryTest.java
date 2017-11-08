package repositorytest;

import org.junit.Before;
import org.junit.Test;
import weather.WeatherReader;
import weatherrepository.WeatherRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Weather repository test.
 */
public class WeatherRepositoryTest {
    private WeatherReader weatherReader;
    private WeatherRepository weatherRepository;
    @Before
    public void setup() {
       weatherReader = mock(WeatherReader.class);
       weatherRepository = new WeatherRepository(weatherReader);
    }
    @Test
    public void testGettingCurrentWeather() throws Exception {
        weatherRepository.getCurrentWeather();
        verify(weatherReader).readJsonFromUrl("weather");
    }
    @Test
    public void testGettingForecast() throws Exception {
        weatherRepository.getThreeDayForecast();
        verify(weatherReader).readJsonFromUrl("forecast");
    }
}
