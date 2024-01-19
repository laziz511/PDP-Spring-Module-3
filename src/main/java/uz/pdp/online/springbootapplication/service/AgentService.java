package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.dto.AgentDTO;
import uz.pdp.online.springbootapplication.dto.FlightDTO;
import uz.pdp.online.springbootapplication.entity.Agent;
import uz.pdp.online.springbootapplication.entity.Flight;
import uz.pdp.online.springbootapplication.mapper.AgentMapper;
import uz.pdp.online.springbootapplication.mapper.FlightMapper;
import uz.pdp.online.springbootapplication.repository.AgentRepository;
import uz.pdp.online.springbootapplication.repository.FlightRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    public AgentDTO findAgentById(Long id) {
        return agentRepository.findById(id)
                .map(agentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Agent not found with ID: " + id));
    }


    public Optional<Agent> findAgentByUsername(String username) {
        return agentRepository.findByUsername(username);
    }

    public Optional<Agent> findAgentByEmail(String email) {
        return agentRepository.findByEmail(email);
    }

    public List<AgentDTO> getAllAgents() {
        List<Agent> agents = agentRepository.findAll();
        return agents.stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AgentDTO saveAgent(AgentDTO agentDTO) {
        Agent agent = agentMapper.toEntity(agentDTO);
        Agent savedAgent = agentRepository.save(agent);
        return agentMapper.toDTO(savedAgent);
    }

    public void deleteAgentById(Long id) {
        agentRepository.deleteById(id);
    }

    public void registerFlights(Long agentId, List<FlightDTO> flights) {
        Optional<Agent> optionalAgent = agentRepository.findById(agentId);
        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            List<Flight> flightEntities = flights.stream()
                    .map(flightMapper::toEntity)
                    .peek(flight -> flight.setAgent(agent))
                    .collect(Collectors.toList());
            flightRepository.saveAll(flightEntities);
            System.out.println("Registered flights for agent: " + agent.getUsername());
        } else {
            throw new RuntimeException("Agent not found with ID: " + agentId);
        }
    }

    public void updateFlightDetails(Long flightId, FlightDTO updatedFlight) {
        Flight flight = flightMapper.toEntity(updatedFlight);
        flight.setId(flightId);
        flightRepository.save(flight);
        System.out.println("Updated flight details for ID " + flightId);
    }


    public void notifyTicketHolders(Long flightId, FlightDTO updatedFlight) {
        Flight flight = flightMapper.toEntity(updatedFlight);
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isPresent()) {
            Flight existingFlight = optionalFlight.get();
            flight.setId(existingFlight.getId());
            String message = "Flight " + updatedFlight.flightNumber() + " details have been updated."
                    + "\nNew departure time: " + updatedFlight.departureTime()
                    + "\nNew arrival time: " + updatedFlight.arrivalTime()
                    + "\nNew price: " + updatedFlight.price();
            System.out.println("Notification sent to ticket holders about flight changes: " + message);
        } else {
            throw new RuntimeException("Flight not found with ID: " + flightId);
        }
    }
}
