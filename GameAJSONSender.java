/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A JSON Sender
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/26/24
 * Revision: 5
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class GameAJSONSender {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8000/receiveGameObject"); // Specify the URL of the web service

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");


            GameObject gameObject = new GameObject("Player1", 100);


            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(gameObject);

            // Log the JSON string being sent
            System.out.println("Sent " + jsonInputString);

            // Send JSON data to the server
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println();

            // Print the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("");
            } else {
                System.out.println("");
            }

            // Parse the JSON response as needed
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
