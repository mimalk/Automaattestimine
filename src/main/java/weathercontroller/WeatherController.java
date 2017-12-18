package weathercontroller;

import utility.FileWriter;
import utility.InputDataStorage;
import weather.*;
import weatherrepository.WeatherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Weather controller.
 */
public class WeatherController {
    private FileWriter fileWriter;
    private InputDataStorage inputDataStorage;

    public WeatherController(FileWriter fileWriter, InputDataStorage inputDataStorage) {
        this.fileWriter = fileWriter;
        this.inputDataStorage = inputDataStorage;
        readFile();
    }
    public void readFile() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("C:/Users/Mirjam/" +
                    "Documents/Java projektid/Automaattestimine/src/main/input.txt"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                inputDataStorage.addCityFromInput(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeOutputToFile(WeatherReader weatherReader) throws Exception {
        WeatherRepository weatherRepository = new WeatherRepository(weatherReader);
        String unit;
        if (weatherReader.getMeasurementUnit().equals("Celsius")) {
            unit = "°C";
        } else {
            unit = "°F";
        }
        CurrentWeather currentWeather = weatherRepository.getCurrentWeather();
        ThreeDayForecast threeDayForecast = weatherRepository.getThreeDayForecast();
        String output = "City: " + weatherReader.getCityName() + getCoordinatesInfo(currentWeather) + "\n"
                + getCurrentWeatherInfo(currentWeather, unit) + "\n" + getForecastInfo(threeDayForecast, unit);
        fileWriter.writeToFile(output, weatherReader.getCityName());
    }
    public void writeOutputsFromInputFile() throws Exception {
        for (String city : inputDataStorage.getCities()) {
            writeOutputToFile(new WeatherReader(city, "Celsius"));
        }
    }
    public String getCurrentWeatherInfo(CurrentWeather currentWeather, String unit) {
        return "Current temperature: " + currentWeather.getCurrentTemperature() + unit;
    }
    public String getForecastInfo(ThreeDayForecast threeDayForecast, String unit) throws Exception {
        String forecastInfo = "";
        for (DailyWeather dailyWeather : threeDayForecast.getThreeDayWeathers()) {
            forecastInfo += dailyWeather.getDate() + " Minimum temperature: " + dailyWeather.getLowestTemperature()
                    + unit + " Maximum temperature: " + dailyWeather.getHighestTemperature() + unit + "\n";
        }
        return forecastInfo;
    }
    public String getCoordinatesInfo(Weather weather) throws Exception {
       return  "\nCoordinates: " + weather.getCoordinates().getLongitude()+ ":" + weather.getCoordinates().getLatitude();
    }
    public InputDataStorage getInputDataStorage() {
        return inputDataStorage;
    }
}
