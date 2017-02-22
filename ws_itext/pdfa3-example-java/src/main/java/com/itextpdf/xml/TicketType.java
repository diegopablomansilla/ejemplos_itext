
package com.itextpdf.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for ticketType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ticketType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="priceNet" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "ticketType", propOrder = {
    "value"
})
public class TicketType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "category")
    protected String category;
    @XmlAttribute(name = "amount")
    protected String amount;
    @XmlAttribute(name = "priceNet")
    protected String priceNet;
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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the priceNet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceNet() {
        return priceNet;
    }

    /**
     * Sets the value of the priceNet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceNet(String value) {
        this.priceNet = value;
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
