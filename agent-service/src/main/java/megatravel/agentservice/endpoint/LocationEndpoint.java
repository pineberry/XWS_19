package megatravel.agentservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import megatravel.agentservice.model.Location;
import megatravel.agentservice.model.LocationRequest;
import megatravel.agentservice.model.LocationResponse;
import megatravel.agentservice.repository.LocationRepository;



@Endpoint
public class LocationEndpoint {

	private static final String NAMESPACE_URI = "http://megatravel.com/location";
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	public LocationEndpoint(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "locationRequest")
	@ResponsePayload
	public LocationResponse getLocation(@RequestPayload LocationRequest request){
		LocationResponse response = new LocationResponse();
		Location location = locationRepository.findById(request.getId()).get();
		response.setLocation(location);
		return response;
	}
	
}
