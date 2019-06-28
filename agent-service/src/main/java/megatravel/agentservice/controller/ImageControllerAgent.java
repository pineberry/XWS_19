package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.agentservice.model.ImageAgent;
import megatravel.agentservice.service.ImageServiceAgent;
import megatravel.backend.model.Image;

@RestController
@RequestMapping("/image-agent")
public class ImageControllerAgent {
	
	@Autowired
	private ImageServiceAgent imageService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/postImage", method = RequestMethod.POST)
	public ResponseEntity<ImageAgent> postImageAgent(@RequestBody String src)
	{
		imageService.create(new ImageAgent(src));
		
		restTemplate.postForObject("http://backend/image/postImage", new Image(src), Image.class);
		
		return new ResponseEntity<ImageAgent>(new ImageAgent(src), HttpStatus.CREATED);
	}

}
