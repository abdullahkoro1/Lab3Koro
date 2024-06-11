/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A JSON Sender
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/9/24
 * Revision: 1
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Represents GameAJSONSender class that sends game object as JSON data over HTTP
 */

public class GameAJSONSender

{
    /**
     * Main method to execute the GameAJSONSender application
     *
     */
    public static void main(String[] args)

    {
        try {


            // Create a connection to the server
            URL url = new URL("http://localhost:8080/receiveGameObject");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);


            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Serialize the game object to JSON
            ObjectMapper mapper = new ObjectMapper();

            GameObject gameObject = new GameObject(); // Create and populate game object

            String jsonInputString = mapper.writeValueAsString(gameObject);

            // Send the JSON data to the server

            try (OutputStream os = conn.getOutputStream())

            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code from the serverr
            int responseCode = conn.getResponseCode();

            System.out.println("POST Response Code :: " + responseCode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
