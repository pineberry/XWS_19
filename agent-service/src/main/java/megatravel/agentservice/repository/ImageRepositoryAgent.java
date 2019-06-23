package megatravel.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.agentservice.model.Image;

public interface ImageRepositoryAgent extends JpaRepository<Image, Long>{

}
