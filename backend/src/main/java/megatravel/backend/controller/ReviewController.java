package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.ReviewListDTO;
import megatravel.backend.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<ReviewListDTO> getAllReviews() {
		ReviewListDTO reviews = new ReviewListDTO();
		reviews.setReviews(reviewService.readAll());
		return new ResponseEntity<ReviewListDTO>(reviews, HttpStatus.OK);
	}
}
