package me.gatiendev.pvpfac;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class request{

    public static String postRequest(String url, String content) throws IOException {
        URL path = new URL(url+"?content="+content);
        HttpURLConnection connection = (HttpURLConnection) path.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int response_code = connection.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputline;
        StringBuffer res = new StringBuffer();
        while((inputline = in.readLine()) != null){
            res.append(inputline);
        }
        in.close();
        return res.toString();
    }

}
