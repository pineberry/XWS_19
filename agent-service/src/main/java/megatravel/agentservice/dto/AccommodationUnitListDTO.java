package megatravel.agentservice.dto;

import java.util.List;

import lombok.Data;
import megatravel.agentservice.model.AccommodationUnit;

@Data
public class AccommodationUnitListDTO {

	private List<AccommodationUnit> accommodationUnits;
	
}
