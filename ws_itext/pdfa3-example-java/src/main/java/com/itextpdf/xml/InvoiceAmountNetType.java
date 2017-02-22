
package com.itextpdf.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for invoiceAmountNetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceAmountNetType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="vat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="priceGross" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceAmountNetType", propOrder = {
    "value"
})
public class InvoiceAmountNetType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "vat")
    protected String vat;
    @XmlAttribute(name = "priceGross")
    protected String priceGross;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the vat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVat() {
        return vat;
    }

    /**
     * Sets the value of the vat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVat(String value) {
        this.vat = value;
    }

    /**
     * Gets the value of the priceGross property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceGross() {
        return priceGross;
    }

    /**
     * Sets the value of the priceGross property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceGross(String value) {
        this.priceGross = value;
    }

}
