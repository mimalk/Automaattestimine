package utilitytest;

import http.utility.HttpUtility;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Http utility test.
 */
public class HttpUtilityTest {
    private static final int HTTP_CODE_SUCCESSFUL = 200;
    @Test
    public void testWeatherAPIHTTPConnectionForCurrentWeather() {
        try {
            HttpUtility httpUtility = new HttpUtility();
            String currentWeatherURL = httpUtility.createWeatherAPIURL("weather", "Tallinn");
            HttpURLConnection connection = httpUtility.makeUrlConnection(currentWeatherURL);
            assertEquals(connection.getResponseCode(), HTTP_CODE_SUCCESSFUL);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testWeatherAPIHTTPConnectionForForecast() {
        try {
            HttpUtility httpUtility = new HttpUtility();
            String forecastURL = httpUtility.createWeatherAPIURL("forecast", "Tallinn");
            HttpURLConnection connection = httpUtility.makeUrlConnection(forecastURL);
            assertEquals(connection.getResponseCode(), HTTP_CODE_SUCCESSFUL);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
