package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.agentservice.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
