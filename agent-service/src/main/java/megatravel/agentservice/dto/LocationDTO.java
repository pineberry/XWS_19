package megatravel.agentservice.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.agentservice.model.LocationAgent;

@Data
public class LocationDTO {

	private Optional<LocationAgent> location;
}
