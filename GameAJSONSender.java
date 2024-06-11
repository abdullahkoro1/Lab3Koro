/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A JSON Sender
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/10/24
 * Revision: 2
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameAJSONSender

{
    public static void main(String[] args)

    {
        try {
            URL url = new URL("http://localhost:8080/receiveGameObject");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            GameObject gameObject = new GameObject("Player1", 100); // Create a GameObject
            String jsonInputString = mapper.writeValueAsString(gameObject);

            try (OutputStream os = conn.getOutputStream())

            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
