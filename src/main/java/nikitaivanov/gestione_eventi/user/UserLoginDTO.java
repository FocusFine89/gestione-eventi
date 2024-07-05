package nikitaivanov.gestione_eventi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @NotEmpty(message = "L'email non può essere vuota")
        @Email(message = "L'email non è valida")
        String email,
        @NotEmpty(message = "La password non può essere vuota")
        String password
) {
}
