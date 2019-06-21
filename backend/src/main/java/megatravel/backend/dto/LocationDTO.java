package megatravel.backend.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Location;

@Data
public class LocationDTO {

	private Optional<Location> location;
}
