
package pl.agh.webservice2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.webservice2 package. 
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

    private final static QName _AddTwoNumbersResponse_QNAME = new QName("http://webservice2.soa.agh.pl/", "addTwoNumbersResponse");
    private final static QName _AddTwoNumbers_QNAME = new QName("http://webservice2.soa.agh.pl/", "addTwoNumbers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.webservice2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddTwoNumbersResponse }
     * 
     */
    public AddTwoNumbersResponse createAddTwoNumbersResponse() {
        return new AddTwoNumbersResponse();
    }

    /**
     * Create an instance of {@link AddTwoNumbersResponse.Return }
     * 
     */
    public AddTwoNumbersResponse.Return createAddTwoNumbersResponseReturn() {
        return new AddTwoNumbersResponse.Return();
    }

    /**
     * Create an instance of {@link AddTwoNumbers }
     * 
     */
    public AddTwoNumbers createAddTwoNumbers() {
        return new AddTwoNumbers();
    }

    /**
     * Create an instance of {@link AddTwoNumbersResponse.Return.Entry }
     * 
     */
    public AddTwoNumbersResponse.Return.Entry createAddTwoNumbersResponseReturnEntry() {
        return new AddTwoNumbersResponse.Return.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTwoNumbersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice2.soa.agh.pl/", name = "addTwoNumbersResponse")
    public JAXBElement<AddTwoNumbersResponse> createAddTwoNumbersResponse(AddTwoNumbersResponse value) {
        return new JAXBElement<AddTwoNumbersResponse>(_AddTwoNumbersResponse_QNAME, AddTwoNumbersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTwoNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice2.soa.agh.pl/", name = "addTwoNumbers")
    public JAXBElement<AddTwoNumbers> createAddTwoNumbers(AddTwoNumbers value) {
        return new JAXBElement<AddTwoNumbers>(_AddTwoNumbers_QNAME, AddTwoNumbers.class, null, value);
    }

}
