package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.entity.Booking;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByFlightId(Long flightId);
}