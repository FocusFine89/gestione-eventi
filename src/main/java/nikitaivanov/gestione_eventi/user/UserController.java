package nikitaivanov.gestione_eventi.user;

import nikitaivanov.gestione_eventi.evento.Evento;
import nikitaivanov.gestione_eventi.evento.EventoService;
import nikitaivanov.gestione_eventi.evento.PrenotaEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EventoService eventoService;

    @GetMapping("/me")
    public User profile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PatchMapping("/eventi/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void prenotazione(@PathVariable long id, @RequestBody PrenotaEventoDTO prenotazione){
        eventoService.prenotazioneEvento(id,prenotazione);
    }

}
