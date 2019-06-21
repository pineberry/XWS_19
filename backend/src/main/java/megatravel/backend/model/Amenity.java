package megatravel.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Amenity", propOrder = {
    "id",
    "amenity"
})
@Entity
@Data
public class Amenity {

	@Id
	@GeneratedValue
    protected long id;
    @XmlElement(required = true)
    protected String amenity;
	
    public Amenity() {
    	
	}

    public Amenity(long id, String amenity) {
		super();
		this.id = id;
		this.amenity = amenity;
    } 
}