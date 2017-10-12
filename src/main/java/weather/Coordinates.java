package weather;

import org.json.JSONObject;

/**
 * Coordinates class.
 */
public class Coordinates {
    private String coordinates;
    public Coordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    @Override
    public String toString() {
        return coordinates;
    }
    public Double getLatitude() throws Exception {
        return (new JSONObject(coordinates)).getDouble("lat");
    }
    public Double getLongitude() throws Exception {
        return (new JSONObject(coordinates)).getDouble("lon");
    }
}
