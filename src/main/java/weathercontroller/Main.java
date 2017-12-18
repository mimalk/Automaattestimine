package weathercontroller;

import utility.FileWriter;
import utility.InputDataStorage;

public class Main {
    public static void main(String[] args) throws Exception {
        WeatherController weatherController = new WeatherController(new FileWriter(), new InputDataStorage());
        weatherController.writeOutputsFromInputFile();
    }
}
