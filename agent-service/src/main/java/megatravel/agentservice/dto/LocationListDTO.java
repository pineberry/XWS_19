package megatravel.agentservice.dto;

import java.util.List;

import lombok.Data;
import megatravel.agentservice.model.LocationAgent;

@Data
public class LocationListDTO {

	private List<LocationAgent> locations;
}
