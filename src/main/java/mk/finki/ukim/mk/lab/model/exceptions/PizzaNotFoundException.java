package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends RuntimeException{
    public PizzaNotFoundException(String pizza){
        super("Pizza with id "+pizza+" could not be found!");
    }
}
