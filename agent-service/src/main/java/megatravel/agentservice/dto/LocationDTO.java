package megatravel.agentservice.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.agentservice.model.Location;

@Data
public class LocationDTO {

	private Optional<Location> location;
}
