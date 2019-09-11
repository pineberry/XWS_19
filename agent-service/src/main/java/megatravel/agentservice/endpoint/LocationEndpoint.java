package megatravel.agentservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import megatravel.agentservice.model.AccommodationUnit;
import megatravel.agentservice.model.Location;
import megatravel.agentservice.repository.AccommodationUnitRepository;
import megatravel.agentservice.repository.LocationRepository;
import megatravel.agentservice.soap.AddAccommodationUnitRequest;
import megatravel.agentservice.soap.AddAccommodationUnitResponse;
import megatravel.agentservice.soap.AddLocationRequest;
import megatravel.agentservice.soap.AddLocationResponse;



@Endpoint
public class LocationEndpoint {

	private static final String NAMESPACE_URI = "http://megatravel.com/schemas";
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private AccommodationUnitRepository accommodationRepository;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addLocationRequest")
	@ResponsePayload
	public AddLocationResponse addLocation(@RequestPayload AddLocationRequest request){
		AddLocationResponse response = new AddLocationResponse();
		Location location = locationRepository.save(request.getLocation());
		response.setLocation(location);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAccommodationUnitRequest")
	@ResponsePayload
	public AddAccommodationUnitResponse addAccommodationUnit(@RequestPayload AddAccommodationUnitRequest request) {
		AddAccommodationUnitResponse response = new AddAccommodationUnitResponse();
		AccommodationUnit accommodation = accommodationRepository.save(request.getAccommodationUnit());
		response.setAccommodationUnit(accommodation);
		return response;
	}
}
