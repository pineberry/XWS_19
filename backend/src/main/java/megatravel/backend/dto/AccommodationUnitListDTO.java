package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.AccommodationUnit;

@Data
public class AccommodationUnitListDTO {

	private List<AccommodationUnit> accommodationUnits;
	
}
