package utilitytest;


import org.junit.Test;
import utility.InputDataStorage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InputDataStorageTest {
    @Test
    public void testAddingData() {
        InputDataStorage inputDataStorage = new InputDataStorage();
        inputDataStorage.addCityFromInput("Tallinn");
        inputDataStorage.addCityFromInput("Tartu");
        assertEquals(Arrays.asList("Tallinn", "Tartu"), inputDataStorage.getCities());
    }
}
