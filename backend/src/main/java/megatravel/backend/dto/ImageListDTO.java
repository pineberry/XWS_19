package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.Image;

@Data
public class ImageListDTO {

	private List<Image> images;
}
