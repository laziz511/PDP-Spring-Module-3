package uz.pdp.online.springbootapplication.mapstruct.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String id;
    private String name;
    private String manufacturer;
    private double price;
}