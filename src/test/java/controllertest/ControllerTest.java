package controllertest;

import mock.MockJsonReader;
import org.junit.Before;
import org.junit.Test;
import utility.FileWriter;
import utility.InputDataStorage;
import weather.WeatherReader;
import weathercontroller.WeatherController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTest {
    private FileWriter fileWriter;
    private WeatherController weatherController;
    private InputDataStorage inputDataStorage;
    private WeatherReader weatherReader;
    @Before
    public void setup() throws Exception {
        fileWriter = mock(FileWriter.class);
        inputDataStorage = mock(InputDataStorage.class);
        weatherReader = mock(WeatherReader.class);
        MockJsonReader mockJsonReader = new MockJsonReader();
        when(weatherReader.readJsonFromUrl("weather")).thenReturn(mockJsonReader.getJson("weather"));
        when(weatherReader.readJsonFromUrl("forecast")).thenReturn(mockJsonReader.getJson("forecast"));
        when(weatherReader.getMeasurementUnit()).thenReturn("Celsius");
        when(weatherReader.getCityName()).thenReturn("Tallinn");
        weatherController = new WeatherController(fileWriter, inputDataStorage);
    }
    @Test
    public void testWritingOutputToFile() throws Exception {
        weatherController.writeOutputToFile(weatherReader);
        verify(fileWriter).writeToFile("City: Tallinn\n" +
                "Coordinates: 24.75:59.44\n" +
                "Current temperature: 1.0°C\n" +
                "2017-12-16 Minimum temperature: -0.73°C Maximum temperature: 2.03°C\n" +
                "2017-12-17 Minimum temperature: -3.93°C Maximum temperature: 1.62°C\n" +
                "2017-12-18 Minimum temperature: -4.02°C Maximum temperature: -0.33°C\n", "Tallinn");
    }
    @Test
    public void testReadingFromInput() throws Exception {
        verify(inputDataStorage).addCityFromInput("Tallinn");
        verify(inputDataStorage).addCityFromInput("Tartu");
        verify(inputDataStorage).addCityFromInput("Narva");
    }
    @Test
    public void testCitiesAmountFromInput() {
        InputDataStorage inputDataStorage = new InputDataStorage();
        WeatherController weatherController = new WeatherController(new FileWriter(), inputDataStorage);
        assertEquals(3, weatherController.getInputDataStorage().getCities().size());
    }
}
