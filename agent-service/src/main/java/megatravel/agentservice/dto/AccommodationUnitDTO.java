package megatravel.agentservice.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.agentservice.model.AccommodationUnitAgent;

@Data
public class AccommodationUnitDTO {

	private Optional<AccommodationUnitAgent> accommodationUnit;

}
