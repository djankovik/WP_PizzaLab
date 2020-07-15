package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PIZZAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizza {
    @Id
    String name;
    String description;
    boolean veggie;

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    List<Ingredient> ingredients;

    public Pizza(String n,String d){
        this.name=n;
        this.description=d;
        this.veggie = false;
        ingredients = new ArrayList<>();
    }

    public Pizza(String n,String d,boolean veggie){
        this.name=n;
        this.description=d;
        this.veggie = veggie;
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.pizzas.add(this);
    }
}
