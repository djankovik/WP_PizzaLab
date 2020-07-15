package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;

public interface PizzaService {
    Pizza createPizza(String name, String description, boolean veggie, List<String> ingredients);
    Pizza createPizzaWithValidation(String name, String description, List<String> ingredients);
    Pizza updatePizza(String name, String description, boolean veggie,List<String> ingredients);
    Pizza updatePizzaWithValidation(String name, String description, List<String> ingredients);
    void deletePizza(String name);
    Page<Pizza> getAllPizzas(int page, int size);
    List<Pizza> listAllPizzas();
    Pizza findByName(String name);
    List<Pizza> getPizzasWithIngredientsLessThan(int maxIngredients);
    List<Ingredient> getCommonIngredients(String pizza1,String pizza2);

    List<Pizza> getAllSpicyPizzas(boolean spicy);
}
