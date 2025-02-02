package nikitaivanov.gestione_eventi.evento;

import nikitaivanov.gestione_eventi.exceptions.NotFoundException;
import nikitaivanov.gestione_eventi.user.User;
import nikitaivanov.gestione_eventi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UserService userService;

    //Creazione Evento
    public Evento saveEvento(EventoDTO newEvento){
        User organizzatore = this.userService.findById(newEvento.OrganizzatoreId());
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

    //Tutti gli eventi
    public Page<Evento> findAllEventi(int pageNumber, int pageSize){
        if(pageSize>100) pageSize=100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return this.eventoRepository.findAll(pageable);
    }

    //Update Evento
    public Evento findByIdAndUpdate(long id, EventoDTO updateEvento){
        Evento foundEvento = this.findById(id);
        User foundUser = this.userService.findById(updateEvento.OrganizzatoreId());
        foundEvento.setData(updateEvento.data());
        foundEvento.setDescrizione(updateEvento.descrizione());
        foundEvento.setPosti(updateEvento.posti());
        foundEvento.setTitolo(updateEvento.titolo());
        foundEvento.setLuogo(updateEvento.luogo());
        foundEvento.setUser(foundUser);
        return this.eventoRepository.save(foundEvento);
    }

     //Prenotazione Evento
    public void prenotazioneEvento(long id, PrenotaEventoDTO posti){
        Evento evento = this.findById(id);
        evento.setPosti(evento.getPosti() - posti.numeroPosti());
        this.eventoRepository.save(evento);
    }

}
