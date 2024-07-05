package nikitaivanov.gestione_eventi.evento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.beans.Transient;
import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Non puoi lasciare il titolo vuoto")
        @Size(min = 5, max = 40, message = "Il titolo deve essere compreso tra i 5 e i 40 caratteri")
        String titolo,
        String descrizione,
        LocalDate data,
        @NotEmpty(message = "Non puoi lasciare il luogo vuoto")
        @Size(min = 3, max = 15, message = "Il luogo deve essere compreso tra i 3 e i 15 caratteri")
        String luogo,
        int posti,
        long OrganizzatoreId

) {
}
