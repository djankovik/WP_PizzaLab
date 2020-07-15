package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository {
    Pizza savePizza(Pizza pizza);
    void deleteById(String name);
    Page<Pizza> getAllPizzas(int page, int size);
    List<Pizza> listAllPizzas();
    Optional<Pizza> findById(String name);
    List<Pizza> getPizzasWithIngredientsLessThan(int maxIngredients);
    List<Ingredient> getCommonIngredients(String pizza1,String pizza2);
    List<Pizza> getSpicyPizzas(boolean spicy);
}
