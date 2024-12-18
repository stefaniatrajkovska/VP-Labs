package mk.finki.ukim.mk.lab1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(Long id){
        super(String.format("Product with id %s is not found",id));
    }
}
