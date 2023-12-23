package uz.pdp.online.springbootapplication;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCreateDTO {
    private String title;
    private String description;
    private Double price;
    private String author;
}
