package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.Location;

@Data
public class LocationListDTO {

	private List<Location> locations;
}
