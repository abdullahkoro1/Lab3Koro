/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game B JSON Receiver
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/10/24
 * Revision: 2
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GameBJSONReceiver

{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(8080))

        {
            System.out.println("Listening on port 8080...");
            while (true)
            {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    StringBuilder jsonInput = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        jsonInput.append(line);
                    }

                    // Convert JSON input to GameObject object using ObjectMapper
                    ObjectMapper mapper = new ObjectMapper();

                    GameObject gameObject = mapper.readValue(jsonInput.toString(), GameObject.class);
                    System.out.println("Received GameObject: " + gameObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
