package megatravel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Image;
import megatravel.backend.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;	
	
	public Image create(String imgSrc)
	{
		Image image = new Image(imgSrc);
		imageRepository.save(image);
		return image;
	}
	
	public List<Image> readAll()
	{
		return imageRepository.findAll();
	}
}
