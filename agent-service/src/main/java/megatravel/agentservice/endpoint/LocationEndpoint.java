package megatravel.agentservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.location.GetLocationRequest;
import com.megatravel.location.GetLocationResponse;
import com.megatravel.location.Location;

import megatravel.agentservice.model.LocationAgent;
import megatravel.agentservice.repository.LocationRepositoryAgent;



@Endpoint
public class LocationEndpoint {

	private static final String NAMESPACE_URI = "http://megatravel.com/location";
	
	@Autowired
	private LocationRepositoryAgent locationRepository;
	
	@Autowired
	public LocationEndpoint(LocationRepositoryAgent locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationRequest")
	@ResponsePayload
	public GetLocationResponse getLocation(@RequestPayload GetLocationRequest request){
		GetLocationResponse response = new GetLocationResponse();
		Location location = new Location();
		LocationAgent temp = locationRepository.findById(request.getId()).get();
		location.setId(temp.getId());
		location.setCity(temp.getCity());
		location.setState(temp.getState());
		location.setAddress(temp.getAddress());
		response.setLocation(location);
		return response;
	}
	
}
