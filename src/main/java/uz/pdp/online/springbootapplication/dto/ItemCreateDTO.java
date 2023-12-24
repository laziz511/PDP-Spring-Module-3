package uz.pdp.online.springbootapplication.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemCreateDTO {
    private String name;
    private String description;
    private Double price;
    private String image;
}
