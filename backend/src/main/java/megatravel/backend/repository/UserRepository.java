package megatravel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
