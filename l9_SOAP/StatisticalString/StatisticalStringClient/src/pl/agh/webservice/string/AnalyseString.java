
package pl.agh.webservice.string;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for analyseString complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="analyseString">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="yourString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "analyseString", propOrder = {
    "yourString"
})
public class AnalyseString {

    @XmlElement(required = true)
    protected String yourString;

    /**
     * Gets the value of the yourString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYourString() {
        return yourString;
    }

    /**
     * Sets the value of the yourString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYourString(String value) {
        this.yourString = value;
    }

}
