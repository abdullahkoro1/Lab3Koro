/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A JSON Sender
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/26/24
 * Revision: 4
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Sends a GameObject as JSON data via HTTP POST to a specified URL
 *
 */
public class GameAJSONSender
{

    /**
     * Main method to send a GameObject as JSON data via HTTP POST
     *
     */
    public static void main(String[] args)
    {
        try
        {
            URL url = new URL("http://localhost:8080/receiveGameObject"); // URL

            // Opening a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true); // Set connection to allow output

            conn.setRequestMethod("POST"); // Using the HTTP POST method
            conn.setRequestProperty("Content-Type", "application/json"); // Set content type as JSON

            // Create a GameObject instance to be sent

            GameObject gameObject = new GameObject("Player1", 100);

            // Convert GameObject to JSON string

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(gameObject);

            // Log the JSON string being sent
            System.out.println("Sending JSON: " + jsonInputString);

            // Send JSON data to the server
            try (OutputStream os = conn.getOutputStream())
            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the HTTP response code
            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            // Get response message
            if (responseCode == HttpURLConnection.HTTP_OK)
            { // success
                System.out.println("POST was successful");
            } else {
                System.out.println("POST request did not work");
            }

            // Disconnect the connection after use
            conn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}