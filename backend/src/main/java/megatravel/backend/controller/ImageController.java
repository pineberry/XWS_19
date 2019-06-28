package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.ImageListDTO;
import megatravel.backend.model.Image;
import megatravel.backend.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<ImageListDTO> getAllImages()
	{
		ImageListDTO images = new ImageListDTO();
		images.setImages(imageService.readAll());
		return new ResponseEntity<ImageListDTO>(images, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postImage", method = RequestMethod.POST)
	public ResponseEntity<Image> postImage(@RequestBody String src)
	{
		imageService.create(src);
		return new ResponseEntity<Image>(new Image(src), HttpStatus.CREATED);
	}
}
