
package pl.agh.soa.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.soa.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRateResponse_QNAME = new QName("http://webservice.soa.agh.pl/", "getRateResponse");
    private final static QName _SellResponse_QNAME = new QName("http://webservice.soa.agh.pl/", "sellResponse");
    private final static QName _GetRate_QNAME = new QName("http://webservice.soa.agh.pl/", "getRate");
    private final static QName _Sell_QNAME = new QName("http://webservice.soa.agh.pl/", "sell");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.soa.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SellResponse }
     * 
     */
    public SellResponse createSellResponse() {
        return new SellResponse();
    }

    /**
     * Create an instance of {@link GetRate }
     * 
     */
    public GetRate createGetRate() {
        return new GetRate();
    }

    /**
     * Create an instance of {@link Sell }
     * 
     */
    public Sell createSell() {
        return new Sell();
    }

    /**
     * Create an instance of {@link GetRateResponse }
     * 
     */
    public GetRateResponse createGetRateResponse() {
        return new GetRateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.soa.agh.pl/", name = "getRateResponse")
    public JAXBElement<GetRateResponse> createGetRateResponse(GetRateResponse value) {
        return new JAXBElement<GetRateResponse>(_GetRateResponse_QNAME, GetRateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SellResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.soa.agh.pl/", name = "sellResponse")
    public JAXBElement<SellResponse> createSellResponse(SellResponse value) {
        return new JAXBElement<SellResponse>(_SellResponse_QNAME, SellResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.soa.agh.pl/", name = "getRate")
    public JAXBElement<GetRate> createGetRate(GetRate value) {
        return new JAXBElement<GetRate>(_GetRate_QNAME, GetRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sell }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.soa.agh.pl/", name = "sell")
    public JAXBElement<Sell> createSell(Sell value) {
        return new JAXBElement<Sell>(_Sell_QNAME, Sell.class, null, value);
    }

}
