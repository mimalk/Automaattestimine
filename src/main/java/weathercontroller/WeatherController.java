package weathercontroller;

import weather.CurrentWeather;
import weather.DailyWeather;
import weather.ThreeDayForecast;
import weather.WeatherReader;
import weatherrepository.WeatherRepository;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Weather controller.
 */
public class WeatherController {
    private WeatherReader weatherReader = new WeatherReader("Celsius");
    public void writeOutputToFile() throws Exception {
        WeatherRepository weatherRepository = new WeatherRepository(weatherReader);
        //weatherReader.setCity();
        String unit;
        if (weatherReader.getMeasurementUnit().equals("Celsius")) {
            unit = "°C";
        } else {
            unit = "°F";
        }
        CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
        String currentWeatherInfo = "Current temperature: " + currentWeather.getCurrentTemperature() + unit;

        ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
        String forecastInfo = "";
        for (DailyWeather dailyWeather : threeDayForecast.getThreeDayWeathers()) {
            forecastInfo += dailyWeather.getDate() + " Minimum temperature: " + dailyWeather.getLowestTemperature()
                    + unit + " Maximum temperature: " + dailyWeather.getHighestTemperature() + unit + "\n";
        }
        String coordinates = "\nCoordinates: " + currentWeather.getCoordinates().getLongitude()+ ":"
                + currentWeather.getCoordinates().getLatitude();

        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("C:/Users/Mirjam/" +
                "Documents/Java projektid/Automaattestimine/src/main/output.txt"));
        String output = "City: " + weatherReader.getCityName() + coordinates + "\n" + currentWeatherInfo + "\n"
                + forecastInfo;
        bufferedWriter.write(output);
        bufferedWriter.close();
    }
    public static void main(String[] args) throws Exception {
        WeatherController weatherController = new WeatherController();
        weatherController.writeOutputToFile();
    }
}
