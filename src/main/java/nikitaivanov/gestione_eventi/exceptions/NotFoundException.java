package nikitaivanov.gestione_eventi.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("Non ci sono Record con id: " + id);
    }

    public NotFoundException(String email){
        super("Non ci sono record con questa email: " + email);
    }
}
