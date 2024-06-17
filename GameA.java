/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/16/24
 * Revision: 3
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents GameA which sends a GameObject over RabbitMQ
 */

public class GameA

{
    private final static String QUEUE_NAME = "gameQueue";

    /**
     * Main method to send a GameObject over RabbitMQ
     *
     */

    public static void main(String[] args) throws Exception
    {

        // Create a connection factory and set the host
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Try-with-resources block



        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel())

        {

            // Declare a queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Create a GameObject instance
            GameObject gameObject = new GameObject("Player1", 100);

            // Convert GameObject to JSON string


            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(gameObject);

            // Debug statement to print the message being sent

            System.out.println("Sending message: " + message);

            // Publish the message to the queue

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
