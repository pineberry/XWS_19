package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.Review;

@Data
public class ReviewListDTO {

	private List<Review> reviews;
	
}
