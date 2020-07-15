package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.NonVeggieIngredientInVeggiePizzaException;
import mk.finki.ukim.mk.lab.model.exceptions.PizzaNotFoundException;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, IngredientRepository ingredientRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Pizza createPizza(String name, String description, boolean veggie, List<String> ingredients) {
        Pizza p = new Pizza(name,description,veggie);
        for(String i:ingredients){
            Ingredient ingr = ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i));
            if(!ingr.isVeggie() && veggie) throw new NonVeggieIngredientInVeggiePizzaException(i);
            p.addIngredient(ingr);
        }
        return pizzaRepository.savePizza(p);
    }

    @Override
    public Pizza createPizzaWithValidation(String name, String description, List<String> ingredients) {
        Pizza p = new Pizza(name,
                description,
                ingredients.stream().map(i -> ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i)).isVeggie()).allMatch(i -> i== true),
                ingredients.stream().map(i -> ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i))).collect(Collectors.toList()));
        return p;
    }

    @Override
    public Pizza updatePizzaWithValidation(String name, String description, List<String> ingredients) {
        Pizza pizza = pizzaRepository.findById(name).orElseThrow(() -> new PizzaNotFoundException(name));
        pizza.setDescription(description);
        pizza.setVeggie(ingredients.stream().map(i -> ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i)).isVeggie()).allMatch(i -> i== true));
        pizza.setIngredients(ingredients.stream().map(i -> ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i))).collect(Collectors.toList()));
        return this.pizzaRepository.savePizza(pizza);
    }

    @Override
    public Pizza updatePizza(String name, String description, boolean veggie, List<String> ingredients) {
        Pizza pizza = pizzaRepository.findById(name).orElseThrow(() -> new PizzaNotFoundException(name));
        pizza.setDescription(description);
        pizza.setVeggie(veggie);
        List<Ingredient> ingr = new ArrayList<>();
        for(String i:ingredients){
            Ingredient someIngredient = ingredientRepository.findById(i).orElseThrow(() -> new IngredientNotFoundException(i));
            if(veggie && !someIngredient.isVeggie()) throw new NonVeggieIngredientInVeggiePizzaException(name);
            ingr.add(someIngredient);
        }
        pizza.setIngredients(ingr);
        return this.pizzaRepository.savePizza(pizza);
    }

    @Override
    public void deletePizza(String name) {
        pizzaRepository.deleteById(name);
    }

    @Override
    public Page<Pizza> getAllPizzas(int page, int size) {
        return this.pizzaRepository.getAllPizzas(page, size);
    }

    @Override
    public List<Pizza> listAllPizzas() {
        return pizzaRepository.listAllPizzas();
    }

    @Override
    public Pizza findByName(String name) {
        return pizzaRepository.findById(name).orElseThrow(() -> new PizzaNotFoundException(name));
    }

    @Override
    public List<Pizza> getPizzasWithIngredientsLessThan(int maxIngredients) {
        return pizzaRepository.getPizzasWithIngredientsLessThan(maxIngredients);
    }

    @Override
    public List<Ingredient> getCommonIngredients(String pizza1, String pizza2) {
        return pizzaRepository.getCommonIngredients(pizza1,pizza2);
    }

    @Override
    public List<Pizza> getAllSpicyPizzas(boolean spicy) {
        return pizzaRepository.getSpicyPizzas(spicy);
    }
}
