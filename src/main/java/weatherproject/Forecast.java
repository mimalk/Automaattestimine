package weatherproject;

import java.util.List;


/**
 * Forecast class.
 */
public class Forecast {
    List<DailyWeather> threeDayWeathers;
    Coordinates coordinates;
    public Forecast(Coordinates coordinates, List<DailyWeather> threeDayWeathers) {
        this.coordinates = coordinates;
        this.threeDayWeathers = threeDayWeathers;
    }
    public List<DailyWeather> getThreeDayWeathers() throws Exception{
        throw new Exception("No imp");
    }
    public Coordinates getCoordinates() throws Exception{
        throw new Exception("No imp");
    }
}
