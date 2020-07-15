package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;

public interface IngredientService {
   Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie);
   Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie);
   void deleteIngredient(String name);
   Page<Ingredient> getAllIngredients(int page, int size);
    List<Ingredient> listAllIngredients();
   Ingredient findByName(String name);
    List<Ingredient> getAllSpicyIngredients(boolean spicy);
    List<Pizza> getPizzasWithIngredient(String ingredient);
}
