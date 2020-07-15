package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientExistsPriorCreationException;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.TooManySpicyIngredientsException;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie) {
        if(ingredientRepository.findById(name).isPresent()) throw new IngredientExistsPriorCreationException(name);
        if(spicy && ingredientRepository.getAllSpicyIngredients(true).size() == 3) throw new TooManySpicyIngredientsException(name);
        return ingredientRepository.saveIngredient(new Ingredient(name,spicy,amount,veggie));
    }

    @Override
    public Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = ingredientRepository.findById(name).orElseThrow(() -> new IngredientNotFoundException(name));
        if(spicy && !ingredient.isSpicy() && ingredientRepository.getAllSpicyIngredients(true).size() == 3) throw new TooManySpicyIngredientsException(name);
        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);
        return this.ingredientRepository.saveIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        for(Pizza p: pizzaRepository.listAllPizzas()){
            p.setIngredients(p.getIngredients().stream().filter(i -> !i.getName().equals(name)).collect(Collectors.toList()));
            pizzaRepository.savePizza(p);
        }
        ingredientRepository.deleteById(name);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return ingredientRepository.getAllIngredients(page,size);
    }

    @Override
    public List<Ingredient> listAllIngredients() {
        return ingredientRepository.listAllIngredients();
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findById(name).orElseThrow(()-> new IngredientNotFoundException(name));
    }

    @Override
    public List<Ingredient> getAllSpicyIngredients(boolean spicy) {
        return ingredientRepository.getAllSpicyIngredients(spicy);
    }

    @Override
    public List<Pizza> getPizzasWithIngredient(String ingredient) {
        return ingredientRepository.getPizzasWithIngredient(ingredient);
    }
}
