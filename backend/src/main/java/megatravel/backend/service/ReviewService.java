package megatravel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Review;
import megatravel.backend.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	//create
	public Review create(Review review)
	{
		return reviewRepository.save(review);
	}
	
	//readAll
	public List<Review> readAll() 
	{
		return reviewRepository.findAll();
	}
}
