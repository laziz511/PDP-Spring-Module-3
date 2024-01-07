package uz.pdp.online.springbootapplication.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Item entity represents an item in the system.")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the item.")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    @Schema(description = "Name of the item. It must be between 3 and 120 characters.", example = "My Item")
    private String name;

    @Schema(description = "Description of the item.")
    private String description;

    @NotNull
    @Min(1)
    @Schema(description = "Price of the item. It must be at least 1.0.", example = "10.99")
    private double price;
}
