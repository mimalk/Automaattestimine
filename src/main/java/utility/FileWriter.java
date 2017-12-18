package utility;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {
    public void writeToFile(String output, String cityName) {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = Files.newBufferedWriter(Paths.get("C:/Users/Mirjam/" +
                    "Documents/Java projektid/Automaattestimine/src/main/java/output/{" + cityName + "}.txt"));
            bufferedWriter.write(output);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
