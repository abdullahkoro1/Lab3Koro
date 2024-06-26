/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game B JSON Receiver
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/26/24
 * Revision: 5
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class GameBJSONReceiver {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/receiveGameObject", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                StringBuilder jsonInput = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonInput.append(line);
                }


                ObjectMapper mapper = new ObjectMapper();
                GameObject gameObject = mapper.readValue(jsonInput.toString(), GameObject.class);


                System.out.println("Received GameObject: " + gameObject.getName() + ", " + gameObject.getScore());


                String response = "Received GameObject";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}
