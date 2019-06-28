package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.Amenity;

@Data
public class AmenityListDTO {

	private List<Amenity> amenities;
	
}
