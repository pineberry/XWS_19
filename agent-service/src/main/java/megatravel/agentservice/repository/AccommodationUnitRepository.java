package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.agentservice.model.AccommodationUnit;

public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long>{

}
