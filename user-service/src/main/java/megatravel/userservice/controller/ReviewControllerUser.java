package megatravel.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-review")
public class ReviewControllerUser {

	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		
		return new ResponseEntity<Review>(reviewService.create(review), HttpStatus.CREATED);
	}	
}
