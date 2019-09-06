package megatravel.backend.dto;

import java.util.ArrayList;
import java.util.Date;
import lombok.Data;

@Data
public class SearchParametersAddtDTO{

	private String city;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	private String type;
	private String category;
	private ArrayList<Boolean> amenities;
	
	public SearchParametersAddtDTO(String city, Date checkin, Date checkout, int numOfGuests, String type,
			String category, ArrayList<Boolean> amenities) {
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
