package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.LocationAgent;

public interface LocationRepositoryAgent extends JpaRepository<LocationAgent, Long> {

}
