/**
 * Project: Space Game - Systems Integration
 * Purpose Details: Game A
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/9/24
 * Revision: 2
 */

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Represents GameA class that sends game data over RabbitMQ
 */

public class GameA

{
    /** The name of the queue for sending game data */

    private final static String QUEUE_NAME = "gameQueue";

    /**
     * Main method to execute the GameA application
     *
     * @throws Exception If an error occurs during RabbitMQ communication
     */

    public static void main(String[] args) throws Exception

    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())

        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);


            String message = "Game data as a flat file or delimited format";

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}