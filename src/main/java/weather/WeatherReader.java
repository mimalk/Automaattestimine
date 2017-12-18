package weather;

import utility.HttpUtility;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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
    public void setCity(String city) {
        this.cityName = city;
    }
    public void setCityFromConsole() {
        System.out.println("Enter city name: ");
        Scanner scanner = new Scanner(System.in);
        this.cityName = scanner.nextLine();
    }
    public JSONObject readJsonFromUrl(String request) throws Exception {
        if (request.equals("weather") || request.equals("forecast")) {
            HttpUtility httpUtility = new HttpUtility();
            HttpURLConnection connection = httpUtility.makeUrlConnection(httpUtility.createWeatherAPIURL(request, cityName));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (FileNotFoundException f) {
                throw new FileNotFoundException("Such city does not exist!");
            }
            return new JSONObject(stringBuilder.toString());
        } else {
            throw new IllegalArgumentException("The request must be \"weather\" or \"forecast\"");
        }
    }
}
