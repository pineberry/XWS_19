package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.ReviewAgent;

public interface ReviewRepositoryAgent extends JpaRepository<ReviewAgent, Long> {

}
