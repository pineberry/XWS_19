package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.UserAgent;

public interface UserRepositoryAgent extends JpaRepository<UserAgent, Long>{

}
