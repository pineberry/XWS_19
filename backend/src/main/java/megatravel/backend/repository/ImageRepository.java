package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
