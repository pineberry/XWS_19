package megatravel.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
