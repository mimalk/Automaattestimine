package weatherproject;

import http.utility.Converter;

/**
 * Class for current weather.
 */
public class CurrentWeather extends Weather{
    private Double currentTemperature;
    private Converter converter = new Converter();
    public CurrentWeather(String cityName, Coordinates coordinates, Double currentTemperature, String measurementUnit) {
        super(cityName, coordinates, measurementUnit);
        this.currentTemperature = currentTemperature;
    }
    public Double getCurrentTemperature() {
        return converter.convertKelvins(currentTemperature, getMeasurementUnit());
    }
}
