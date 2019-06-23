package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.Amenity;

public interface AmenityRepositoryAgent extends JpaRepository<Amenity, Long>{

}
