
package com.itextpdf.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for totalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="totalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subtotal" type="{}subtotalType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="invoiceAmount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "totalType", propOrder = {
    "subtotal"
})
public class TotalType {

    @XmlElement(required = true)
    protected SubtotalType subtotal;
    @XmlAttribute(name = "invoiceAmount")
    protected String invoiceAmount;

    /**
     * Gets the value of the subtotal property.
     * 
     * @return
     *     possible object is
     *     {@link SubtotalType }
     *     
     */
    public SubtotalType getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the value of the subtotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubtotalType }
     *     
     */
    public void setSubtotal(SubtotalType value) {
        this.subtotal = value;
    }

    /**
     * Gets the value of the invoiceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * Sets the value of the invoiceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceAmount(String value) {
        this.invoiceAmount = value;
    }

}
