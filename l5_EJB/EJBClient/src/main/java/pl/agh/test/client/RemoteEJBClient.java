package pl.agh.test.client;

import pl.agh.test.ejb.*;
import pl.agh.test.ejb.ConverterBean.ConverterBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

public class RemoteEJBClient {

    //private final static java.util.logging.Logger logger = Logger.getLogger(RemoteEJBClient.class .getName());
   // private final static Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
/*
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "remote://localhost:4447";
    private static final String EJB_JNDI_DEPLOYED = "ejb:/EJBService//TheatreInfoBean!pl.agh.test.ejb.TheatreInfo";
    private static final String EJB_JNDI_DEPLOYED2 = "ejb:/EJBService//TheatreBookerBean!pl.agh.test.ejb.TheatreBooker?stateful";
*/

    private final static Logger logger = Logger.getLogger(RemoteEJBClient.class .getName());


    public static void main(String[] args) throws Exception {
        testRemoteEJB();
    }

    private static void testRemoteEJB() throws NamingException {
        logger.info("BEGIN");
        //jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        /*

        Properties properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, PROVIDER_URL);
        properties.put("jboss.naming.client.ejb.context", true);
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        Context initContext = new InitialContext(properties);

        TheatreInfo theatreInfo= (TheatreInfo) initContext.lookup(EJB_JNDI_DEPLOYED);

        */
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        final Context context = new InitialContext(jndiProperties);

        //TheatreInfo theatreInfo= (TheatreInfo) context.lookup(EJB_JNDI_DEPLOYED);

        final TheatreInfo theatreInfo = lookupTheatreInfoEJB(context);
        final TheatreBooker theatreBooker = lookupTheatreBookerEJB(context);
        final Converter converter = lookupTheatreConverterrEJB(context);
        //TheatreBooker theatreBooker= (TheatreBooker)initContext.lookup(EJB_JNDI_DEPLOYED);

        logger.info("Przed, zamieniam 10.0 F na C");
       // logger.info(theatreInfo.printSeatList());
       // try {
            //logger.info(theatreBooker.bookSeat(1));
            logger.info(String.valueOf(converter.Fahr2Cels(60.0)));
       /* } catch (SeatBookedException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }*/
        logger.info("Po ");
       // logger.info(theatreInfo.printSeatList());


        logger.info("END");
       // context.close();
//logger.info(theatreInfo.printSeatList());
        //final TheatreInfo info = lookupTheatreInfoEJB(); //[1]
       // final TheatreBooker book = lookupTheatreBookerEJB(); //[2]


    }

    private static Converter lookupTheatreConverterrEJB(Context context) throws NamingException {

        return (Converter) context.lookup("ejb:/EJBService/ConverterBean!pl.agh.test.ejb.Converter");
    }

    private static TheatreInfo lookupTheatreInfoEJB(Context context) throws NamingException {

        return (TheatreInfo) context.lookup("ejb:/EJBService/TheatreInfoBean!pl.agh.test.ejb.TheatreInfo");
    }

    private static TheatreBooker lookupTheatreBookerEJB(Context context)  throws NamingException {

        return (TheatreBooker) context.lookup("ejb:/EJBService//TheatreBookerBean!pl.agh.test.ejb.TheatreBooker?stateful");
    }

}