package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
