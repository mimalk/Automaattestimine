package weatherproject;

import java.util.List;


/**
 * ThreeDayForecast class.
 */
public class ThreeDayForecast extends Weather {
    private List<DailyWeather> threeDayWeathers;
    public ThreeDayForecast(String cityName, Coordinates coordinates, List<DailyWeather> threeDayWeathers, String measurementUnit) {
        super(cityName, coordinates, measurementUnit);
        this.threeDayWeathers = threeDayWeathers;
    }
    public List<DailyWeather> getThreeDayWeathers() throws Exception {
        return threeDayWeathers;
    }
}
