package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaPizzaRepository extends JpaRepository<Pizza,String> {
    @Query("SELECT p FROM Pizza p " +
            "WHERE COUNT(p.ingredients) < :ingredientNumber")
    List<Pizza> getPizzasWithIngredientsLessThan(@Param("ingredientNumber") int ingredientNumber);

    @Query("SELECT p FROM Pizza p WHERE EXISTS (SELECT i FROM p.ingredients i WHERE i.spicy = :spicy)")
    List<Pizza> getSpicyPizzas(@Param("spicy") boolean spicy);

    //List<Pizza> findAllByIngredients_SpicyIsTrue();
}
