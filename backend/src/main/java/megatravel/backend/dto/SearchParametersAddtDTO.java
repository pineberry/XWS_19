package megatravel.backend.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;
import megatravel.backend.model.Amenity;

@Data
public class SearchParametersAddtDTO{

	private String city;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	private String type;
	private String category;
	private List<Amenity> amenities;
	
	public SearchParametersAddtDTO(String city, Date checkin, Date checkout, int numOfGuests, String type,
			String category, List<Amenity> amenities) {
		super();
		this.city = city;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.type = type;
		this.category = category;
		this.amenities = amenities;
	}
	
	
	
}
