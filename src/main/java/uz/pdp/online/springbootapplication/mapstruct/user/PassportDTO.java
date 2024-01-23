package uz.pdp.online.springbootapplication.mapstruct.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassportDTO {
    private String serial;
    private String number;
}
