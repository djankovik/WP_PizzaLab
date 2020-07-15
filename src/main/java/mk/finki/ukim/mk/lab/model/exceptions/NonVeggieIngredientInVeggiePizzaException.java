package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonVeggieIngredientInVeggiePizzaException extends RuntimeException{
    public NonVeggieIngredientInVeggiePizzaException(String pizza){
        super("The veggie pizza "+pizza+" can't contain non-veggie ingredients!");
    }
}
