
package pl.agh.soa.webservice.string;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.soa.webservice.string package. 
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

    private final static QName _AnalyseString_QNAME = new QName("http://string.webservice.soa.agh.pl/", "analyseString");
    private final static QName _AnalyseStringResponse_QNAME = new QName("http://string.webservice.soa.agh.pl/", "analyseStringResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.soa.webservice.string
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnalyseStringResponse }
     * 
     */
    public AnalyseStringResponse createAnalyseStringResponse() {
        return new AnalyseStringResponse();
    }

    /**
     * Create an instance of {@link AnalyseStringResponse.ReturnedHashMap }
     * 
     */
    public AnalyseStringResponse.ReturnedHashMap createAnalyseStringResponseReturnedHashMap() {
        return new AnalyseStringResponse.ReturnedHashMap();
    }

    /**
     * Create an instance of {@link AnalyseString }
     * 
     */
    public AnalyseString createAnalyseString() {
        return new AnalyseString();
    }

    /**
     * Create an instance of {@link AnalyseStringResponse.ReturnedHashMap.Entry }
     * 
     */
    public AnalyseStringResponse.ReturnedHashMap.Entry createAnalyseStringResponseReturnedHashMapEntry() {
        return new AnalyseStringResponse.ReturnedHashMap.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnalyseString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://string.webservice.soa.agh.pl/", name = "analyseString")
    public JAXBElement<AnalyseString> createAnalyseString(AnalyseString value) {
        return new JAXBElement<AnalyseString>(_AnalyseString_QNAME, AnalyseString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnalyseStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://string.webservice.soa.agh.pl/", name = "analyseStringResponse")
    public JAXBElement<AnalyseStringResponse> createAnalyseStringResponse(AnalyseStringResponse value) {
        return new JAXBElement<AnalyseStringResponse>(_AnalyseStringResponse_QNAME, AnalyseStringResponse.class, null, value);
    }

}
