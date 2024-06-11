/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game B
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/9/24
 * Revision: 1
 */

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * Represents the GameB class that receives game data from GameA over RabbitMQ
 */

public class GameB

{
    private final static String QUEUE_NAME = "gameQueue";

    /**
     * Main method to execute the GameB application
     *
     * @throws Exception If an error occurs during RabbitMQ communication
     */

    public static void main(String[] args) throws Exception
    {
        // Create a connection factory

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        // Establish connection and channel to RabbitMQ server

        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel())
        {

            // Declare the queue

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // Callback function to handle incoming messages

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };

            // Start consuming messages from the queue

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        }
    }
}
