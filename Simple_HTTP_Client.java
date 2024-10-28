//Objective: Build an HTTP client to retrieve HTML content from a web page.

//        Steps:
//        1. Create a new Java class named SimpleHttpClient.
//        2. Write code to perform an HTTP GET request to a web page (e.g.,
//        https://www.uom.lk).
//        3. Set the appropriate request headers, such as Accept (content type)
//        4. Read and print the HTML content of the web page.
//        5. Handle exceptions appropriately


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

public class Simple_HTTP_Client {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.youtube.com/");

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "text/html");
            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(5000);

            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Response: " + response.toString());
            } else {
                System.err.println("GET request failed. Response Code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

}
