package uz.pdp.online.springbootapplication.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Store entity represents a store in the system.")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the store.")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    @Schema(description = "Name of the store. It must be between 3 and 120 characters.", example = "My Store")
    private String name;

    @NotBlank
    @Size(min = 9, max = 250)
    @Email
    @Schema(description = "Email of the store. It must be between 9 and 250 characters.", example = "store@example.com")
    private String email;

    @NotNull
    @Min(1)
    @Schema(description = "Capacity of the store. It must be at least 1.", example = "100")
    private int capacity;

    @Schema(description = "Description of the store.", example = "This is a great store with many products.")
    private String description;
}
