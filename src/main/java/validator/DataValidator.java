package validator;

import weatherproject.Coordinates;

/**
 * Class that validates data.
 */
public class DataValidator {
    public void validateCoordinates(Coordinates coordinates) throws Exception{
        double lonMin = -180;
        double lonMax = 180;
        double latMin = -90;
        double latMax = 90;
        if (coordinates.getLatitude() > latMax || coordinates.getLatitude() < latMin) {
            throw new Exception("The latitude of coordinates is not valid!");
        } else if (coordinates.getLongitude() > lonMax || coordinates.getLongitude() < lonMin) {
            throw new Exception("The longitude of coordinates is not valid!");
        }
    }
    public void validateTemperature(Double temperature, String measurementUnit) throws Exception {
        int maxTemp;
        int minTemp;
        if (measurementUnit.equals("Celsius")) {
            maxTemp = 60;
            minTemp = -60;
        } else if (measurementUnit.equals("Fahrenheit")) {
            maxTemp = 140;
            minTemp = -76;
        } else {
            throw new IllegalArgumentException("Units of measurement are not correct");
        }
        if (temperature == null) {
            throw new IllegalArgumentException("Temperature is 'null'");
        } else if ((temperature < minTemp) || (temperature > maxTemp)) {
            throw new IllegalArgumentException("Temperature must be between " + minTemp + "-" + maxTemp
                    + ". Temperature shown in the report is " + temperature + ".");
        }
    }
}

