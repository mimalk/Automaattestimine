package weatherproject;

import http.utility.HttpUtility;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Reads the weather info from weather API.
 */
public class WeatherReader {
    private String cityName;
    private String measurementUnit;
    public WeatherReader(String cityName, String measurementUnit) {
        this.measurementUnit = measurementUnit;
        this.cityName = cityName;
    }
    public String getMeasurementUnit() {
        return measurementUnit;
    }
    public String getCityName() {
        return cityName;
    }
    public JSONObject readJsonFromUrl(String request) throws Exception {
        HttpUtility httpUtility = new HttpUtility();
        HttpURLConnection connection = httpUtility.makeUrlConnection(httpUtility.createWeatherAPIURL(request, cityName));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();

        return new JSONObject(stringBuilder.toString());
    }
}
