package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.Location;

public interface LocationRepositoryAgent extends JpaRepository<Location, Long> {

}
