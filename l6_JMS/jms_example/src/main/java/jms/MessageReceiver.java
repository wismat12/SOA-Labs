package jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.Enumeration;

@Stateless
public class MessageReceiver {

    private static final String DEFAULT_USERNAME = "mateuszJMS";
    private static final String DEFAULT_PASSWORD = "jms";
    
    @Resource(mappedName = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "java:jboss/exported/jms/queue/test")
    Queue myQueue;
    
    private String message;
    
    public String receiveMessage() {
        try {

            Connection connection = connectionFactory.createConnection(DEFAULT_USERNAME,DEFAULT_PASSWORD);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser queueBrowser = session.createBrowser(myQueue);

            Enumeration enumeration = queueBrowser.getEnumeration();

            while (enumeration.hasMoreElements()) {

                TextMessage o = (TextMessage) enumeration.nextElement();
                return "Receive " + o.getText();

            }

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "";
    }
}