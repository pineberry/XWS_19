package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
