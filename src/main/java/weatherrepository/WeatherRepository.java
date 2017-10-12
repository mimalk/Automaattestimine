package weatherrepository;

import org.json.JSONArray;
import org.json.JSONObject;
import weatherproject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * WeatherRepository class that uses the info and gives current weather and 3 day forecast.
 */
public class WeatherRepository {
    private WeatherReader weatherReader;
    public WeatherRepository(WeatherReader weatherReader) {
        this.weatherReader = weatherReader;
    }
    public CurrentWeather getCurrentWeather() throws Exception {
        JSONObject json = weatherReader.readJsonFromUrl("weather");
        JSONObject main = new JSONObject(json.get("main").toString());
        String cityName = json.getString("name");
        Double currentTemp = main.getDouble("temp");
        Coordinates coordinates = new Coordinates(json.get("coord").toString());
        return new CurrentWeather(cityName, coordinates, currentTemp, weatherReader.getMeasurementUnit());
    }
    public ThreeDayForecast getThreeDayForecast() throws Exception{
        JSONObject json = weatherReader.readJsonFromUrl("forecast");
        JSONArray array = json.getJSONArray("list");
        JSONObject jsonCity = new JSONObject(json.get("city").toString());
        String coords = jsonCity.get("coord").toString();
        Coordinates coordinates = new Coordinates(coords);
        String cityName = jsonCity.getString("name");

        String wantedDate = LocalDate.now().plusDays(1).toString();
        List<DailyWeather> dailyWeathers = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject listElement = new JSONObject(array.get(i).toString());
            String date = listElement.getString("dt_txt").substring(0, 10);
            if (date.equals(wantedDate)) {
                int start = i;
                for (int m = 0; m < 3; m++) {
                    List<JSONObject> oneDay = new ArrayList<>();
                    for (int k = 0; k < 8; k++) {
                        oneDay.add(new JSONObject(array.get(start).toString()));
                        start++;
                    }
                    dailyWeathers.add(new DailyWeather(oneDay, weatherReader.getMeasurementUnit()));
                }
                break;
            }
        }
        return new ThreeDayForecast(cityName, coordinates, dailyWeathers, weatherReader.getMeasurementUnit());
    }
}
