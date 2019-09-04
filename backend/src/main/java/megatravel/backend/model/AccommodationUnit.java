package megatravel.backend.model;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccommodationUnit", propOrder = {
		"id",
		"hostId",
		"location",
		"type",
		"category",
		"description",
		"unitCapacity",
	    "images",
	    "amenities",
	    "cancelationPeriod",
	    "defaultPrice",
	    "pricePlan",
	    "bookedDates"
})
@Entity
@Table
@Data
public class AccommodationUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@XmlElement(required = true)
	private long hostId;
	@XmlElement(required = true)
	@OneToOne(targetEntity = Location.class, cascade = CascadeType.MERGE)
	private Location location;
	@XmlElement(required = true)
	private String type;
	@XmlElement(required = true)
	private String category;
	@XmlElement(required = true)
	private String description;
	@XmlElement(name = "unit_capacity")
	private int unitCapacity;
	@XmlElement(required = true)
	@OneToMany(targetEntity = Image.class)
	private List<Image> images;
	@XmlElement(required = true)
	@ElementCollection
	private Map<String, Boolean> amenities;
	@XmlElement(name = "cancelation_period")
	@XmlSchemaType(name = "unsignedInt")
	private long cancelationPeriod;
	@XmlElement(required = true)
	private double defaultPrice;
	@XmlElement(required = true)
	@ElementCollection
	private Map<String, Double> pricePlan;
    @XmlElement(required = true)
    @ElementCollection
    private List<String> bookedDates;
	
    public AccommodationUnit() {
		
	}

	public AccommodationUnit(Long id, long hostId, Location location, String type, String category, String description,
			int unitCapacity, List<Image> images, Map<String, Boolean> amenities, long cancelationPeriod,
			double defaultPrice, Map<String, Double> pricePlan, List<String> bookedDates) {
		super();
		this.id = id;
		this.hostId = hostId;
		this.location = location;
		this.type = type;
		this.category = category;
		this.description = description;
		this.unitCapacity = unitCapacity;
		this.images = images;
		this.amenities = amenities;
		this.cancelationPeriod = cancelationPeriod;
		this.defaultPrice = defaultPrice;
		this.pricePlan = pricePlan;
		this.bookedDates = bookedDates;
	}

	public AccommodationUnit(long hostId, Location location, String type, String category, String description,
			int unitCapacity, List<Image> images, Map<String, Boolean> amenities, long cancelationPeriod,
			double defaultPrice, Map<String, Double> pricePlan, List<String> bookedDates) {
		super();
		this.hostId = hostId;
		this.location = location;
		this.type = type;
		this.category = category;
		this.description = description;
		this.unitCapacity = unitCapacity;
		this.images = images;
		this.amenities = amenities;
		this.cancelationPeriod = cancelationPeriod;
		this.defaultPrice = defaultPrice;
		this.pricePlan = pricePlan;
		this.bookedDates = bookedDates;
	}
    
}
