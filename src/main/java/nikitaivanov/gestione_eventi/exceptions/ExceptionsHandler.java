package nikitaivanov.gestione_eventi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    //Bad Request Exception
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleBadRequest(BadRequestException ex){
        if(ex.getErrorList()!= null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage() ).collect(Collectors.joining(". "));
            return new ErrorsDTO(message);
        }else{
            return new ErrorsDTO(ex.getMessage());
        }
    }

    //Not Found Exception
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage());

    }


    //Unauthorized Exception
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorize(UnauthorizedException ex){
        return new ErrorsDTO(ex.getMessage());

    }



    //Internal Error excpetion
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO ErrorsDTO(Exception ex){
        ex.printStackTrace();
        return new ErrorsDTO("Problema lato server, Risolveremo presto!");
    }



}
