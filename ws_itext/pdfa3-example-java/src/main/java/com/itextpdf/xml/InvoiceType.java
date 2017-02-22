package com.itextpdf.xml;

import javax.xml.bind.annotation.*;

/**
 * <p>
 * Java class for invoiceType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tickets" type="{}ticketsType"/>
 *         &lt;element name="total" type="{}totalType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceType", propOrder = { "tickets", "total" })
@XmlRootElement(name = "invoice")
public class InvoiceType {

	@XmlElement(required = true)
	protected TicketsType tickets;
	@XmlElement(required = true)
	protected TotalType total;
	@XmlAttribute(name = "number")
	protected String number;
	@XmlAttribute(name = "name")
	protected String name;

	/**
	 * Gets the value of the tickets property.
	 * 
	 * @return possible object is {@link TicketsType }
	 * 
	 */
	public TicketsType getTickets() {
		return tickets;
	}

	/**
	 * Sets the value of the tickets property.
	 * 
	 * @param value
	 *            allowed object is {@link TicketsType }
	 * 
	 */
	public void setTickets(TicketsType value) {
		this.tickets = value;
	}

	/**
	 * Gets the value of the total property.
	 * 
	 * @return possible object is {@link TotalType }
	 * 
	 */
	public TotalType getTotal() {
		return total;
	}

	/**
	 * Sets the value of the total property.
	 * 
	 * @param value
	 *            allowed object is {@link TotalType }
	 * 
	 */
	public void setTotal(TotalType value) {
		this.total = value;
	}

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNumber(String value) {
		this.number = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

}
