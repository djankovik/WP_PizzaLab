package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientNotFoundException extends RuntimeException{
    public IngredientNotFoundException(String ingredient){
        super("Ingredient with id "+ingredient+" could not be found!");
    }
}
