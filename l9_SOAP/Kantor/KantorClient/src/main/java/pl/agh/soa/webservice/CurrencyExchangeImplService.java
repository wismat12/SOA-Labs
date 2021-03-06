
package pl.agh.soa.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CurrencyExchangeImplService", targetNamespace = "http://webservice.soa.agh.pl/", wsdlLocation = "http://localhost:8080/KantorService/CurrencyExchangeImpl?wsdl")
public class CurrencyExchangeImplService
    extends Service
{

    private final static URL CURRENCYEXCHANGEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CURRENCYEXCHANGEIMPLSERVICE_EXCEPTION;
    private final static QName CURRENCYEXCHANGEIMPLSERVICE_QNAME = new QName("http://webservice.soa.agh.pl/", "CurrencyExchangeImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/KantorService/CurrencyExchangeImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CURRENCYEXCHANGEIMPLSERVICE_WSDL_LOCATION = url;
        CURRENCYEXCHANGEIMPLSERVICE_EXCEPTION = e;
    }

    public CurrencyExchangeImplService() {
        super(__getWsdlLocation(), CURRENCYEXCHANGEIMPLSERVICE_QNAME);
    }

    public CurrencyExchangeImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CURRENCYEXCHANGEIMPLSERVICE_QNAME, features);
    }

    public CurrencyExchangeImplService(URL wsdlLocation) {
        super(wsdlLocation, CURRENCYEXCHANGEIMPLSERVICE_QNAME);
    }

    public CurrencyExchangeImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CURRENCYEXCHANGEIMPLSERVICE_QNAME, features);
    }

    public CurrencyExchangeImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CurrencyExchangeImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CurrencyExchange
     */
    @WebEndpoint(name = "CurrencyExchangeImplPort")
    public CurrencyExchange getCurrencyExchangeImplPort() {
        return super.getPort(new QName("http://webservice.soa.agh.pl/", "CurrencyExchangeImplPort"), CurrencyExchange.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CurrencyExchange
     */
    @WebEndpoint(name = "CurrencyExchangeImplPort")
    public CurrencyExchange getCurrencyExchangeImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.soa.agh.pl/", "CurrencyExchangeImplPort"), CurrencyExchange.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CURRENCYEXCHANGEIMPLSERVICE_EXCEPTION!= null) {
            throw CURRENCYEXCHANGEIMPLSERVICE_EXCEPTION;
        }
        return CURRENCYEXCHANGEIMPLSERVICE_WSDL_LOCATION;
    }

}
