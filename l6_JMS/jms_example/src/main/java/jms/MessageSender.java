package jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.jms.JMSContext;
import javax.naming.NamingException;
import java.util.logging.Logger;


@Stateless
public class MessageSender {

    private static final String DEFAULT_USERNAME = "mateuszJMS";
    private static final String DEFAULT_PASSWORD = "jms";

    private static final Logger log = Logger.getLogger(MessageSender.class.getName());
    
    @Resource(mappedName = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "java:jboss/exported/jms/queue/test")
    Queue queue;
    
    public void sendMessage(String message) {

        MessageProducer messageProducer;
        TextMessage textMessage;

        try {

            Connection connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();
            //textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            textMessage.setText(message);
            messageProducer.send(textMessage);
            
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}