package uz.pdp.online.springbootapplication.dto;

import java.time.LocalDateTime;

public record FlightDTO(Long id, String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime,
                        int numberOfTickets, double price, AirportDTO departureAirport, AirportDTO arrivalAirport,
                        AgentDTO agent) {
}