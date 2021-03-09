package com.francisco.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class TesteConsumidorTopicoEstoqueSelector {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.setClientID("estoque");

        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topico = (Topic) context.lookup("loja");

        MessageConsumer consumer =
                session.createDurableSubscriber(
                        topico, "assinatura-selector", "ebook is null OR ebook=false", false);

        consumer.setMessageListener(message -> {

            TextMessage textMessage = (TextMessage) message;

            try {
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
