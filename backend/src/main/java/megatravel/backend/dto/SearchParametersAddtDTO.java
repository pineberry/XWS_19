package megatravel.backend.dto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Amenity;
import megatravel.backend.model.Location;

@Data
public class SearchParametersAddtDTO{

	private Optional<Location> location;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	private String type;
	private String category;
	private List<Amenity> amenities;
	
	public SearchParametersAddtDTO(Optional<Location> location, Date checkin, Date checkout, int numOfGuests, String type,
			String category, List<Amenity> amenities) {
		super();
		this.location = location;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.type = type;
		this.category = category;
		this.amenities = amenities;
	}
	
	
	
}
