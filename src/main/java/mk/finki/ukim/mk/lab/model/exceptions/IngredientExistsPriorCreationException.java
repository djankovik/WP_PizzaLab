package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
    public class IngredientExistsPriorCreationException extends RuntimeException{
        public IngredientExistsPriorCreationException(String ingredient){
            super("The ingredient "+ingredient+" already exists and can't be created again!");
        }
}
