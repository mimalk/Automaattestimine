package weather;

import org.json.JSONException;
import utility.Converter;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for daily weather.
 */
public class DailyWeather {
    private List<JSONObject> oneDayWeatherInfo = new ArrayList<>();
    private Converter converter = new Converter();
    private String measurementUnit;
    private String date;
    public DailyWeather(List<JSONObject> oneDayWeatherInfo, String measurementUnit) throws JSONException {
        this.measurementUnit = measurementUnit;
        this.oneDayWeatherInfo = oneDayWeatherInfo;
        date = oneDayWeatherInfo.get(0).get("dt_txt").toString().split(" ")[0];
    }
    public List<JSONObject> getOneDayWeatherInfo() {
        return oneDayWeatherInfo;
    }
    public String getDate() throws JSONException {
        return date;
    }
    public double findMinOrMax(String key) throws Exception {
        JSONObject oneWeather = new JSONObject(oneDayWeatherInfo.get(0).get("main").toString());
        Double WantedTemp = oneWeather.getDouble(key);
        for (int i = 1; i < oneDayWeatherInfo.size(); i++) {
            oneWeather = new JSONObject(oneDayWeatherInfo.get(i).get("main").toString());
            Double temp = oneWeather.getDouble(key);
            if (key.equals("temp_max")) {
                if (temp > WantedTemp) {
                    WantedTemp = temp;
                }
            } else {
                if (temp < WantedTemp) {
                    WantedTemp = temp;
                }
            }
        }
        return WantedTemp;
    }

    public Double getLowestTemperature() throws Exception {
        return converter.convertKelvins(findMinOrMax("temp_min"), measurementUnit);
    }
    public Double getHighestTemperature() throws Exception {
        return converter.convertKelvins(findMinOrMax("temp_max"), measurementUnit);
    }
}
