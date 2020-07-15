package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaIngredientRepository extends JpaRepository<Ingredient, String> {
    @Query("SELECT i FROM Ingredient i " +
            "WHERE i.spicy = true")
    List<Ingredient> getAllSpicyIngredients();

}
