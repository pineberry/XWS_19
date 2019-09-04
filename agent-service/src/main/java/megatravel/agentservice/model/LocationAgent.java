package megatravel.agentservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    "address"
})
@Entity
@Data
public class LocationAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @XmlElement(required = true)
    private String state;
    @XmlElement(required = true)
    private String city;
    @XmlElement(required = true)
    private String address;
    
	public LocationAgent(String state, String city, String address) {
		super();
		this.state = state;
		this.city = city;
		this.address = address;
	}
	public LocationAgent() {

	}
	public LocationAgent(long id, String state, String city, String address) {
		super();
		this.id = id;
		this.state = state;
		this.city = city;
		this.address = address;
	}
    
}
