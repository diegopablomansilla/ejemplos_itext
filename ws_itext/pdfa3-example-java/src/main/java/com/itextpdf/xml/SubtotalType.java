
package com.itextpdf.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subtotalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subtotalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceAmountNet" type="{}invoiceAmountNetType"/>
 *         &lt;element name="includedVat" type="{}includedVatType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="priceGross" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subtotalType", propOrder = {
    "invoiceAmountNet",
    "includedVat"
})
public class SubtotalType {

    @XmlElement(required = true)
    protected InvoiceAmountNetType invoiceAmountNet;
    @XmlElement(required = true)
    protected IncludedVatType includedVat;
    @XmlAttribute(name = "priceGross")
    protected String priceGross;

    /**
     * Gets the value of the invoiceAmountNet property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceAmountNetType }
     *     
     */
    public InvoiceAmountNetType getInvoiceAmountNet() {
        return invoiceAmountNet;
    }

    /**
     * Sets the value of the invoiceAmountNet property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceAmountNetType }
     *     
     */
    public void setInvoiceAmountNet(InvoiceAmountNetType value) {
        this.invoiceAmountNet = value;
    }

    /**
     * Gets the value of the includedVat property.
     * 
     * @return
     *     possible object is
     *     {@link IncludedVatType }
     *     
     */
    public IncludedVatType getIncludedVat() {
        return includedVat;
    }

    /**
     * Sets the value of the includedVat property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncludedVatType }
     *     
     */
    public void setIncludedVat(IncludedVatType value) {
        this.includedVat = value;
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
