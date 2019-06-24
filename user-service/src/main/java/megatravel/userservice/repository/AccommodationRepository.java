package megatravel.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.AccommodationUnit;

public interface AccommodationRepository extends JpaRepository<AccommodationUnit, Long>{

}
