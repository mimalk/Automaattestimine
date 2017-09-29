package weatherproject;

/**
 * Class for current weather.
 */
public class CurrentWeather {
    private String currentTemperature;
    private Coordinates coordinates;
    public CurrentWeather(Coordinates coordinates, String currentTemperature) {
        this.currentTemperature = currentTemperature;
        this.coordinates = coordinates;
    }
    public String getCurrentTemperature() {
        return currentTemperature;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
}
