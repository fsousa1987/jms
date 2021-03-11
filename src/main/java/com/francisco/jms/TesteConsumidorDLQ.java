package com.francisco.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class TesteConsumidorDLQ {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("DLQ");
        MessageConsumer consumer = session.createConsumer(fila);

        consumer.setMessageListener(System.out::println);

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}
