package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(mappedName = "java:jboss/exported/jms/topic/test", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:jboss/exported/jms/topic/test"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "messageSelector",
                propertyValue = "subjectList='list2' OR chosen='consumer3'")
})
public class Consumer3 implements MessageListener {
    private static final Logger logger = Logger.getLogger(Consumer3.class.getName());
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tm = (TextMessage) message;
        } catch (ClassCastException e) {
            logger.warning("Received message is not text message");
        }
        try {
            logger.info(this + " received message: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

