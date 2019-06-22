package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long>{

}
