package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.springbootapplication.entity.Agent;
import uz.pdp.online.springbootapplication.entity.Airport;
import uz.pdp.online.springbootapplication.entity.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirport(Airport airport);

    List<Flight> findByArrivalAirport(Airport airport);

    List<Flight> findByAgent(Agent agent);

    List<Flight> findByDepartureAirport_City_Id(Long id);
}