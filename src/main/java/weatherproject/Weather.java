package weatherproject;

/**
 * Super class.
 */
public class Weather {
    private String cityName;
    private Coordinates coordinates;
    private String measurementUnit;

    public Weather(String cityName, Coordinates coordinates, String measurementUnit) {
        this.measurementUnit = measurementUnit;
        this.coordinates = coordinates;
        this.cityName = cityName;
    }
    public String getMeasurementUnit() {
        return measurementUnit;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public String getCityName() {
        return cityName;
    }
}
