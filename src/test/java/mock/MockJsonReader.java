package mock;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MockJsonReader {
    public JSONObject getJson(String request) throws IOException, JSONException {
        BufferedReader bufferedReader;
        if (request.equals("weather")) {
            bufferedReader = Files.newBufferedReader(Paths.get("C:/Users/Mirjam/" +
                    "Documents/Java projektid/Automaattestimine/src/test/java/mock/mockcurrentweatherjson.txt"));

        } else {
            bufferedReader = Files.newBufferedReader(Paths.get("C:/Users/Mirjam/" +
                    "Documents/Java projektid/Automaattestimine/src/test/java/mock/mockforecastjson.txt"));
        }
        String jsonString = bufferedReader.readLine();
        JSONObject currentWeatherJson = new JSONObject(jsonString);
        bufferedReader.close();
        return currentWeatherJson;
    }

    public static void main(String[] args) throws IOException, JSONException {
        System.out.println(new MockJsonReader().getJson("forecast"));
    }
}
