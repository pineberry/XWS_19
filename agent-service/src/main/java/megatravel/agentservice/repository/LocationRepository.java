package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import megatravel.agentservice.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
