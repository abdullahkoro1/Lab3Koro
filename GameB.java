/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game B
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/16/24
 * Revision: 2
 */
import com.rabbitmq.client.*;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a consumer application that receives GameObject messages from a RabbitMQ queue
 */
public class GameB

{

    /**
     * The name of the RabbitMQ queue to consume messages from
     */

    private final static String QUEUE_NAME = "gameQueue";

    /**
     * Main method to consume GameObject messages from RabbitMQ queue
     *
     */
    public static void main(String[] args) throws Exception

    {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // Callback to handle incoming messages
            DeliverCallback deliverCallback = (consumerTag, delivery) ->
            {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received: '" + message + "'"); // Debug statement

                // Convert JSON message to GameObject
                ObjectMapper mapper = new ObjectMapper();

                GameObject gameObject = mapper.readValue(message, GameObject.class);

                System.out.println("GameObject: " + gameObject.getName() + ", " + gameObject.getScore());
            };

            // Consume messages from the queue
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        }
    }
}
