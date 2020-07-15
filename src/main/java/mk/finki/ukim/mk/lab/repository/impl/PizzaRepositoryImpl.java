package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.PizzaNotFoundException;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
    private final JpaPizzaRepository jpaPizzaRepository;

    public PizzaRepositoryImpl(JpaPizzaRepository jpaPizzaRepository) {
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @Override
    public Pizza savePizza(Pizza pizza) {
        return this.jpaPizzaRepository.save(pizza);
    }

    @Override
    public void deleteById(String name) {
        this.jpaPizzaRepository.deleteById(name);
    }

    @Override
    public Page<Pizza> getAllPizzas(int page, int size) {
        org.springframework.data.domain.Page<Pizza> result = this.jpaPizzaRepository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public List<Pizza> listAllPizzas() {
        return jpaPizzaRepository.findAll();
    }

    @Override
    public Optional<Pizza> findById(String name) {
        return jpaPizzaRepository.findById(name);
    }

    @Override
    public List<Pizza> getPizzasWithIngredientsLessThan(int maxIngredients) {
        return jpaPizzaRepository.findAll().stream().filter(p -> p.getIngredients().size() <= maxIngredients).collect(Collectors.toList());//jpaPizzaRepository.getPizzasWithIngredientsLessThan(maxIngredients);
    }

    @Override
    public List<Ingredient> getCommonIngredients(String pizza1, String pizza2) {
        HashSet<Ingredient> i1 = new HashSet<>(jpaPizzaRepository.findById(pizza1).orElseThrow(() -> new PizzaNotFoundException(pizza1)).getIngredients());
        HashSet<Ingredient> i2 = new HashSet<>(jpaPizzaRepository.findById(pizza2).orElseThrow(() -> new PizzaNotFoundException(pizza2)).getIngredients());
        i1.retainAll(i2);
        return new ArrayList<>(i1);
    }

    @Override
    public List<Pizza> getSpicyPizzas(boolean spicy) {
        return jpaPizzaRepository.getSpicyPizzas(spicy);
    }
}
