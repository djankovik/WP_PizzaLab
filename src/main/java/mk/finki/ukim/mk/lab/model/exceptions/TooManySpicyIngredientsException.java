package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManySpicyIngredientsException extends RuntimeException {
    public TooManySpicyIngredientsException(String ingredient){
        super("The spicy ingredient "+ingredient+" can't be created. The menu can't have more than 3 spicy items!");
    }
}