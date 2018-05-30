package jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class MessageSender {

    private static final String DEFAULT_USERNAME = "mateuszJMS";
    private static final String DEFAULT_PASSWORD = "jms";


    @Resource(mappedName = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "java:jboss/exported/jms/topic/test")
    Topic topic;
    
    public void sendMsg(String msgsubjectList, String msgChosen, String message) {

        MessageProducer messageProducer;
        TextMessage textMessage;

        try {

            Connection connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(topic);
            textMessage = session.createTextMessage();

            if (msgsubjectList != null && !msgsubjectList.isEmpty())
                textMessage.setStringProperty("subjectList", msgsubjectList);

            if (msgChosen != null && !msgChosen.isEmpty())
                textMessage.setStringProperty("chosen", msgChosen);

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