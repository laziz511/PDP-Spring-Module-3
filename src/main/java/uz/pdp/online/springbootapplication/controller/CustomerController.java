package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.dto.CustomerDTO;
import uz.pdp.online.springbootapplication.dto.FlightDTO;
import uz.pdp.online.springbootapplication.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/flights")
    public ResponseEntity<List<FlightDTO>> viewFlightsByCity(@RequestParam Long cityId) {
        List<FlightDTO> flights = customerService.getFlightsByCity(cityId);
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/{userid}/flights/{flightId}/book")
    public ResponseEntity<Void> bookFlight(@PathVariable Long userid, @PathVariable Long flightId) {
        customerService.bookFlight(flightId, userid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/flights/{flightId}/cancel")
    public ResponseEntity<Void> cancelBookedFlight(@PathVariable Long flightId) {
        customerService.cancelBookedFlight(flightId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{customerId}/delete")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
