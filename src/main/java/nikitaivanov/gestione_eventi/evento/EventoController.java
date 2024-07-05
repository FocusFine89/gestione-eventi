package nikitaivanov.gestione_eventi.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    //Lista di tutti gli eventi
    @GetMapping
    public Page<Evento> findAllEventi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return eventoService.findAllEventi(page, size);
    }

    //Crea evento
    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento saveEvento(@RequestBody @Validated EventoDTO evento){
        return eventoService.saveEvento(evento);
    }

    //Eliminazione Evento
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void deleteEvento(@PathVariable long id){
        this.eventoService.deleteById(id);
    }

    //Modifica Evento
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento updateEvento(@PathVariable long id, @RequestBody @Validated EventoDTO evento){
        return this.eventoService.findByIdAndUpdate(id,evento);
    }

}
