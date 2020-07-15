package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    private final JpaIngredientRepository jpaIngredientRepository;

    public IngredientRepositoryImpl(JpaIngredientRepository jpaIngredientRepository) {
        this.jpaIngredientRepository = jpaIngredientRepository;
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return jpaIngredientRepository.save(ingredient);
    }

    @Override
    public void deleteById(String name) {
        jpaIngredientRepository.deleteById(name);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        org.springframework.data.domain.Page<Ingredient> result = this.jpaIngredientRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name")));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public List<Ingredient> listAllIngredients() {
        return jpaIngredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(String name) {
        return jpaIngredientRepository.findById(name);
    }

    @Override
    public List<Ingredient> getAllSpicyIngredients(boolean spicy) {
        return jpaIngredientRepository.getAllSpicyIngredients();
    }

    @Override
    public List<Pizza> getPizzasWithIngredient(String ingredient) {
        return jpaIngredientRepository.findById(ingredient).orElseThrow(IllegalArgumentException::new).getPizzas();
    }
}
