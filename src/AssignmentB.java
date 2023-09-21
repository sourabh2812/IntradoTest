import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AssignmentB {

    public static void main(String[] args) {
        try {
            // Define the API endpoint
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=Toronto,ca&APPID=00eda2ef9a4b05c9594665f065edc144";

            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            httpURLConnection.setRequestMethod("GET");

            // Get the response code
            int responseCode = httpURLConnection.getResponseCode();

            // If the response code is 200 (HTTP_OK), read the input stream and parse the temperature
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close the BufferedReader
                in.close();

                // Convert the content StringBuilder to a String
                String response = content.toString();

                // Find the index of the temperature in the response string
                String temperatureKey = "\"temp\":";
                int index = response.indexOf(temperatureKey);

                if (index != -1) {
                    int startIndex = index + temperatureKey.length();
                    int endIndex = response.indexOf(",", startIndex);
                    if (endIndex != -1) {
                        String temperatureString = response.substring(startIndex, endIndex);
                        try {
                            double temperatureInKelvin = Double.parseDouble(temperatureString);
                            double temperatureInCelsius = temperatureInKelvin - 273.15;
                            System.out.println("The outside temperature is: " + temperatureInCelsius + "°C");
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing temperature: " + temperatureString);
                        }
                    }
                }
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

            // Disconnect the HttpURLConnection
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
