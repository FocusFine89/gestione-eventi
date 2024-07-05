package nikitaivanov.gestione_eventi.user;

import nikitaivanov.gestione_eventi.exceptions.BadRequestException;
import nikitaivanov.gestione_eventi.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder bCrypt;

    //Creazione User
    public User saveUser(UserDTO newUser){
        this.userRepository.findByEmail(newUser.email()).ifPresent(user -> {
            throw new BadRequestException("Utente gia presente con l'email: " + newUser.email());
        });
        User user = new User(newUser.role(), newUser.name(), newUser.email(), bCrypt.encode(newUser.password()));
        return userRepository.save(user);
    }

    //Cerca per id
    public User findById(long id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //Cerca per email
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }

}
