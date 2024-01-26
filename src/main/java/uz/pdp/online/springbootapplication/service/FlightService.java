package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.dto.FlightDTO;
import uz.pdp.online.springbootapplication.entity.Flight;
import uz.pdp.online.springbootapplication.mapper.FlightMapper;
import uz.pdp.online.springbootapplication.repository.FlightRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FlightDTO getFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        return optionalFlight.map(flightMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    public void addFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);
        flightRepository.save(flight);
        System.out.println("Added flight: " + flight);
    }

    public void deleteFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            flightRepository.deleteById(id);
            System.out.println("Flight deleted with ID: " + id);
        } else {
            throw new RuntimeException("Flight not found with ID: " + id);
        }
    }
}
