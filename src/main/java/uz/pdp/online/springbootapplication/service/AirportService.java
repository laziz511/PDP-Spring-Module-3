package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.dto.AirportDTO;
import uz.pdp.online.springbootapplication.dto.CityDTO;
import uz.pdp.online.springbootapplication.entity.Airport;
import uz.pdp.online.springbootapplication.entity.City;
import uz.pdp.online.springbootapplication.mapper.AirportMapper;
import uz.pdp.online.springbootapplication.mapper.CityMapper;
import uz.pdp.online.springbootapplication.repository.AirportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;
    private final CityMapper cityMapper;

    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AirportDTO getAirportById(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        return optionalAirport.map(airportMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + id));
    }

    public List<AirportDTO> getAirportsByCity(CityDTO cityDTO) {
        City city = cityMapper.toEntity(cityDTO);
        List<Airport> airports = airportRepository.findByCity(city);
        return airports.stream()
                .map(airportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.toEntity(airportDTO);
        airportRepository.save(airport);
        System.out.println("Added airport: " + airport);
    }

    public void deleteAirportById(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            airportRepository.deleteById(id);
            System.out.println("Airport deleted with ID: " + id);
        } else {
            throw new RuntimeException("Airport not found with ID: " + id);
        }
    }
}