package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.ReviewAgent;
import megatravel.agentservice.repository.ReviewRepositoryAgent;

@Service
public class ReviewServiceAgent {

	@Autowired
	private ReviewRepositoryAgent reviewRepositoryAgent;
	
	public ReviewAgent create(ReviewAgent review)
	{
		return reviewRepositoryAgent.save(review);
	}
	
	public List<ReviewAgent> readAll() 
	{
		return reviewRepositoryAgent.findAll();
	}
	
	public Optional<ReviewAgent> readById(Long id)
	{
		return reviewRepositoryAgent.findById(id);
	}
	
}
