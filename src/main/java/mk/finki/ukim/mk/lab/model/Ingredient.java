package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="INGREDIENTS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    @Id
    String name;
    boolean spicy;
    float amount;
    boolean veggie;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    @NotFound(action = NotFoundAction.IGNORE)
    List<Pizza> pizzas;

    public Ingredient(String name,boolean spicy,float amount,boolean veggie){
        this.name = name;
        this.spicy = spicy;
        this.amount = amount;
        this.veggie = veggie;
        pizzas = new ArrayList<>();
    }
    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
        pizza.ingredients.add(this);
    }

}
