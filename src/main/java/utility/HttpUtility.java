package utility;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP utility class.
 */
public class HttpUtility {

    public String createWeatherAPIURL(String request, String city) {
        return "http://api.openweathermap.org/data/2.5/" + request + "?q=" + city + "&APPID=bdf14735ead69f8a84eb9694a9591883";
    }

    public HttpURLConnection makeUrlConnection(String url) throws Exception{
        URL apiUrl = new URL(url);
        return (HttpURLConnection) apiUrl.openConnection();
    }
}
