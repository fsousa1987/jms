package com.francisco.jms.log;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class TesteConsumidorFilaLog {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("LOG");
        MessageConsumer consumer = session.createConsumer(fila);

        consumer.setMessageListener(message -> {

            TextMessage textMessage = (TextMessage) message;

            try {
                // message.acknowledge();
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}
