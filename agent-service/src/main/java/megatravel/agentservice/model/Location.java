package megatravel.agentservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "id",
    "state",
    "city",
    "address",
    "latitude",
    "longitude"
})
@Entity
@Data
public class Location {

	@Id
	@GeneratedValue
    private long id;
    @XmlElement(required = true)
    private String state;
    @XmlElement(required = true)
    private String city;
    @XmlElement(required = true)
    private String address;
    private double latitude;
    private double longitude;
}
