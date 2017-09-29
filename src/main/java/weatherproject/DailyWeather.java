package weatherproject;

import java.util.List;

/**
 * Class for daily weather.
 */
public class DailyWeather {
    private List<String> temperatures;
    public DailyWeather(List<String> temperatures) {
        this.temperatures = temperatures;
    }
    public String getLowestTemperature() throws Exception {
        throw new Exception("No imp");
    }
    public String getHighestTemperature() throws Exception {
        throw new Exception("No imp");
    }
}
