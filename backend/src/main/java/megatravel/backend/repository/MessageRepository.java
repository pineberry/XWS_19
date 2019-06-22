package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
