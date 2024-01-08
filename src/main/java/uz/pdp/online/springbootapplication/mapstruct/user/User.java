package uz.pdp.online.springbootapplication.mapstruct.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String username;
    private String password;

    private String passportSerial;
    private String passportNumber;

    private String addressRegion;
    private String addressCity;
    private String addressStreet;
    private String addressHome;
}
