package nikitaivanov.gestione_eventi.security;

import nikitaivanov.gestione_eventi.exceptions.UnauthorizedException;
import nikitaivanov.gestione_eventi.user.User;
import nikitaivanov.gestione_eventi.user.UserDTO;
import nikitaivanov.gestione_eventi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bCrypt;


    public String loginAndCreateToken(UserDTO payload){
        User user = this.userService.findByEmail(payload.email());
        if (bCrypt.matches(payload.password(), user.getPassword())){
            return jwtTools.createToken(user);
        }else{
            throw new UnauthorizedException("Email o Password non corrette, riprova!");
        }

    }

}
