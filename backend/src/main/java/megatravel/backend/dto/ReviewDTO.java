package megatravel.backend.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Review;

@Data
public class ReviewDTO {

	private Optional<Review> review;
	
}
