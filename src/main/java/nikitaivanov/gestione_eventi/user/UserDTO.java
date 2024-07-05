package nikitaivanov.gestione_eventi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "Non puoi lasciare il nome vuoto")
        @Size(min = 3, max = 15, message = "Il nome deve essere compreso tra i 3 e i 15 caratteri")
        String name,
        @NotEmpty(message = "Non puoi lasciare l'email vuota")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "Non puoi lasciare la password vuota")
        @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
        String password,
        Role role
) {
}
