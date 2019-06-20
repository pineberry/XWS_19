package megatravel.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.userservice.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
