package nikitaivanov.gestione_eventi.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.ListResourceBundle;

public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorList;

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList){
        super("Ci sono stati errori nel Payload");
        this.errorList = errorList;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }
}
