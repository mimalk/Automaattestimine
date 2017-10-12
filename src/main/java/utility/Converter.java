package utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Converter.
 */
public class Converter {

    public Double convertKelvins(Double tempInKelvins, String measurementUnit) throws IllegalArgumentException {
        BigDecimal bigDecimal;
        if (measurementUnit.equals("Celsius")) {
            bigDecimal = new BigDecimal(tempInKelvins - 273.15);
        } else if (measurementUnit.equals("Fahrenheit")) {
            bigDecimal = new BigDecimal(tempInKelvins * 9/5 - 459.67);
        } else {
            throw new IllegalArgumentException("Measurement unit is not valid");
        }
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
