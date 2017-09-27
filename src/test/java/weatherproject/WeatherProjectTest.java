package weatherproject;

import http.utility.HttpUtility;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;


/**
 * Tests for the weather API.
 */
public class WeatherProjectTest {
    private static int HTTP_CODE_SUCCESSFUL = 200;
    @Test
    public void testCurrentTemperature(){
    }

    @Test
    public void testCoordinates() {
    }
    @Test
    public void testForecastHighestTemperature() {

    }
    @Test
    public void testForecastLowestTemperature() {

    }
    @Test
    public void testWeatherAPIHTTPConnection() {
        try {
            String weatherAPIURL = HttpUtility.createWeatherAPIURL();
            HttpURLConnection connection = HttpUtility.makeUrlConnection(weatherAPIURL);
            assertEquals(connection.getResponseCode(), HTTP_CODE_SUCCESSFUL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testRequestCityEqualsResponseCity() {

    }

}
