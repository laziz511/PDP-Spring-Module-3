package uz.pdp.online.springbootapplication.mailing.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.springbootapplication.mailing.user.User;
import uz.pdp.online.springbootapplication.mailing.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        registrationService.sendActivationEmail(user);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
