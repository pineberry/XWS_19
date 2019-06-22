package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{

}
