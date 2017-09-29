package weatherproject;

import http.utility.HttpUtility;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests for the weather API.
 */
public class WeatherProjectTest {
    private static int HTTP_CODE_SUCCESSFUL = 200;
    private WeatherReader weatherReader;
    private Weather weather;
    private DataValidator dataValidator;
    @Before
    public void setVariables() {
        this.weatherReader = new WeatherReader("Tallinn");
        this.weather = new Weather();
        this.dataValidator = new DataValidator();
    }
    @Test
    public void testCurrentTemperature(){
        try {
            CurrentWeather currentWeather = weather.getCurrentWeather(weatherReader);
            dataValidator.validateTemperature(currentWeather.getCurrentTemperature());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCoordinates() {
        try {
            CurrentWeather currentWeather = weather.getCurrentWeather(weatherReader);
            dataValidator.validateCoordinates(currentWeather.getCoordinates());
        } catch (Exception e) {
           fail();
        }
    }
    @Test
    public void testForecastHighestTemperature() {
        try {
            Forecast forecast = weather.getThreeDayForecast(weatherReader);
            for (DailyWeather dailyWeather : forecast.getThreeDayWeathers()) {
                dataValidator.validateTemperature(dailyWeather.getHighestTemperature());
            }
        } catch (Exception exe) {
            fail();
        }

    }
    @Test
    public void testForecastLowestTemperature() {
        try {
            Forecast forecast = weather.getThreeDayForecast(weatherReader);
            for (DailyWeather dailyWeather : forecast.getThreeDayWeathers()) {
                dataValidator.validateTemperature(dailyWeather.getLowestTemperature());
            }
        } catch (Exception exe) {
            fail();
        }
    }
    @Test
    public void testWeatherAPIHTTPConnection() {
        try {
            String weatherAPIURL = HttpUtility.createWeatherAPIURL();
            HttpURLConnection connection = HttpUtility.makeUrlConnection(weatherAPIURL);
            assertEquals(connection.getResponseCode(), HTTP_CODE_SUCCESSFUL);
        } catch (Exception e) {
            fail();
        }

    }

}
