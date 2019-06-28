package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}
