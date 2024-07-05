package nikitaivanov.gestione_eventi.security;

import nikitaivanov.gestione_eventi.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private UserService userService;

    //Login
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO payload){
        return new UserLoginResponseDTO(authService.loginAndCreateToken(payload));
    }

    //Registrazione
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserDTO user){
        return userService.saveUser(user);
    }

}
