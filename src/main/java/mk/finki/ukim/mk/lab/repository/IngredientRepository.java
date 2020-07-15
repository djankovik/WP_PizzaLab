package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Ingredient saveIngredient(Ingredient ingredient);
    void deleteById(String name);
    Page<Ingredient> getAllIngredients(int page, int size);
    List<Ingredient> listAllIngredients();
    Optional<Ingredient> findById(String name);
    List<Ingredient> getAllSpicyIngredients(boolean spicy);
    List<Pizza> getPizzasWithIngredient(String ingredient);
}
