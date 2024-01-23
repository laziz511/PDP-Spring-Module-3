package uz.pdp.online.springbootapplication.configuration.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    private String url;
    private String username;
    private String password;
}
