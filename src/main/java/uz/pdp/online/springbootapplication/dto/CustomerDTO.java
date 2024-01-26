package uz.pdp.online.springbootapplication.dto;

public record CustomerDTO(Long id, String username, String email, CityDTO cityOfResidence) {
}
