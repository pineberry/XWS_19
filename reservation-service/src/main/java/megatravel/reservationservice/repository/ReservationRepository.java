package megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import megatravel.backend.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
