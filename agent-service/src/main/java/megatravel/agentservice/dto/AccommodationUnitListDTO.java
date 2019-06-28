package megatravel.agentservice.dto;

import java.util.List;

import lombok.Data;
import megatravel.agentservice.model.AccommodationUnitAgent;

@Data
public class AccommodationUnitListDTO {

	private List<AccommodationUnitAgent> accommodationUnits;
	
}
