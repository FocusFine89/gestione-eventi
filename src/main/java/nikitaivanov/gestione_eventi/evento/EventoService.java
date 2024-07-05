package nikitaivanov.gestione_eventi.evento;

import nikitaivanov.gestione_eventi.exceptions.NotFoundException;
import nikitaivanov.gestione_eventi.user.User;
import nikitaivanov.gestione_eventi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UserService userService;

    //Creazione Evento
    public Evento saveEvento(long id, EventoDTO newEvento){
        User organizzatore = this.userService.findById(id);
        Evento evento = new Evento(newEvento.titolo(), newEvento.descrizione(), newEvento.data(), newEvento.luogo(), newEvento.posti(), organizzatore);
        return eventoRepository.save(evento);

    }

    //Cerca evento per ID
    public Evento findById(long id){
        return this.eventoRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //Eliminazione Evento
    public void deleteById(long id){
        Evento evento = this.findById(id);
        this.eventoRepository.delete(evento);
    }

}
