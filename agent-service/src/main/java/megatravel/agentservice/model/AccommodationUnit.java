//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.11 at 01:28:27 PM CEST 
//


package megatravel.agentservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;


/**
 * <p>Java class for accommodationUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accommodationUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="hostId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="location" type="{http://megatravel.com/schemas}location"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitCapacity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="amenities">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cancelationPeriod" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pricePlan">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="bookedDates" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accommodationUnit", propOrder = {
    "id",
    "hostId",
    "location",
    "type",
    "category",
    "description",
    "unitCapacity",
    "amenities",
    "cancelationPeriod",
    "defaultPrice",
    "pricePlan",
    "bookedDates"
})
@Data
@Entity
@Table
public class AccommodationUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
	@XmlElement(required = true)
	protected long hostId;
    @XmlElement(required = true)
    @OneToOne(targetEntity = Location.class, cascade = CascadeType.MERGE)
    protected Location location;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String category;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected int unitCapacity;
    @XmlElement(required = true)
    @ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "accommodation_unit_id"))
	private Map<String, Boolean> amenities;	
    @XmlElement(required = true)
    protected long cancelationPeriod;
    @XmlElement(required = true)
    protected double defaultPrice;
    @XmlElement(required = true)
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "accommodation_unit_id"))
	private Map<String, Double> pricePlan;
    @XmlElement(required = true)@ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "accommodation_unit_id"))
    protected List<String> bookedDates;

}
