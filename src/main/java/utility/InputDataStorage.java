package utility;

import java.util.ArrayList;
import java.util.List;

public class InputDataStorage {
    private List<String> cities = new ArrayList<>();
    public void addCityFromInput(String city) {
        cities.add(city);
    }
    public List<String> getCities() {
        return cities;
    }
}
