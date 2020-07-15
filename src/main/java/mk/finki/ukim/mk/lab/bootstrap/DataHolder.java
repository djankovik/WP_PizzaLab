package mk.finki.ukim.mk.lab.bootstrap;


import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.stereotype.Component;
import lombok.Getter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static final List<Pizza> pizzas = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<Ingredient> ingredients = new ArrayList<>();

    public final JpaIngredientRepository ingredientRepository;
    public final JpaPizzaRepository pizzaRepository;
    public final JpaOrderRepository orderRepository;

    public DataHolder(JpaIngredientRepository ingredientRepository, JpaPizzaRepository pizzaRepository, JpaOrderRepository orderRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }


    @PostConstruct
    public void init() {
        ingredients.add(new Ingredient("tomato_sauce",false, (float) 14.0,true));//0
        ingredients.add(new Ingredient("mozzarella",false, (float) 1.0,true));//1
        ingredients.add(new Ingredient("ham",false, (float) 1.0,false));//2
        ingredients.add(new Ingredient("mushrooms",false, (float) 18.0,true));//3
        ingredients.add(new Ingredient("onion",false, (float) 20.0,true));//4
        ingredients.add(new Ingredient("fresh_tomato",false, (float) 1.0,true));//5
        ingredients.add(new Ingredient("pineapple",false, (float) 1.0,true));//6
        ingredients.add(new Ingredient("eggs",false, (float) 1.0,false));//7
        ingredients.add(new Ingredient("shrimp",false, (float) 4.0,false));//8
        ingredients.add(new Ingredient("parmesan",false, (float) 0.5,true));//9
        ingredients.add(new Ingredient("oregano",false, (float) 0.2,true));//10
        ingredients.add(new Ingredient("jalapenos",true, (float) 1.0,true));//11
        ingredients.add(new Ingredient("olives",false, (float) 7.0,true));//12
        ingredients.add(new Ingredient("tuna",false, (float) 2.0,false));//13
        ingredients.add(new Ingredient("crab",false, (float) 1.0,false));//14
        ingredients.add(new Ingredient("peperoni",true, (float) 7.3,false));//15
        ingredients.add(new Ingredient("minced_meat",false, (float) 0.3,false));//16
        ingredients.add(new Ingredient("minced_beef",false, (float) 0.2,false));//17
        ingredients.add(new Ingredient("artichoke",false, (float) 0.2,true));//18
        ingredients.add(new Ingredient("spicy_sauce",true, (float) 0.5,true));//19
        ingredients.add(new Ingredient("doner_kebab",false, (float) 0.7,false));//20
        ingredients.add(new Ingredient("german_sausage",false, (float) 0.15,false));//21
        ingredients.add(new Ingredient("rucola",false, (float) 0.5,true));//22

        pizzas.add(new Pizza("Capricciosa","tomato sauce, mozzarella, mushrooms, ham, eggs, artichoke, cocktail sausages, green olives",false));//0
        pizzas.add(new Pizza("Fungi","tomato sauce, mozzarella, mushrooms",true));//1
        pizzas.add(new Pizza("Vegetariana","tomato sauce, mozzarella, mushrooms, onion, (artichoke), sweet corn, green peppers",true));//2
        pizzas.add(new Pizza("Marinara","tomato sauce, mozzarella, shrimps, mussels, tuna, calamari, crab meat",false));//3
        pizzas.add(new Pizza("Margherita","tomato sauce, mozzarella",true));//4
        pizzas.add(new Pizza("Peperoni","tomato sauce, mozzarella, peperoni",false));//5
        pizzas.add(new Pizza("Mexicana","tomato sauce, mozzarella, various recipes with minced beef, jalapenos, sweet corn, onion, spicy sauce and other hot ingredients",false));//6
        pizzas.add(new Pizza("Romana","tomato, mozzarella, anchovies, oregano",false));//7
        pizzas.add(new Pizza("Kebabpizza","tomato sauce, mozzarella, döner kebab, onion, green peperoncini, (kebab sauce poured over after baking)",false));//8
        pizzas.add(new Pizza("Viennese","tomato, mozzarella, German sausage, oregano",false));//9
        pizzas.add(new Pizza("Rucola","tomato sauce, mozzarella di bufala, parma ham, Parmesan shavings, rucola",true));//10
        pizzas.add(new Pizza("Calzone","tomato sauce, mozzarella, mushrooms, ham, eggs",false));//11
        pizzas.add(new Pizza("Bolognese","tomato sauce, mozzarella, minced meat, onion, (fresh tomato)",false));//12
        pizzas.add(new Pizza("Hawaii","tomato sauce, mozzarella, ham, pineapple",false));//13
        pizzas.add(new Pizza("Quatro formaggi","tomato sauce, and 4 assorted cheeses, generally mozzarella, Parmesan cheese, blue cheese, and goat cheese, but may vary",true));//14

        orders.add(new Order("Capricciosa","Small","Bobby","2407  Kincheloe Road"));
        orders.add(new Order("Hawaii","Medium","Angela","2378  Big Elm"));
        orders.add(new Order("Bolognese","Small","Maya","2684  Edsel Road"));
        orders.add(new Order("Vegetariana","Big","Toby","1885  Sunburst Drive"));
        orders.add(new Order("Rucola","Big","Alex","413  White Lane"));
        orders.add(new Order("Rucola","Small","Nick","4463  Quiet Valley Lane"));
        orders.add(new Order("Bolognese","Medium","Adam","4728  Pringle Drive"));
        orders.add(new Order("Quatro formaggi","Family","Eve","1905  Quilly Lane"));
        orders.add(new Order("Calzone","Medium","Martin","3263  Levy Court"));



        if (this.pizzaRepository.count() == 0) {
            this.ingredientRepository.saveAll(ingredients);
            for(Pizza p:pizzas){
                p.addIngredient(ingredients.get(0));
                p.addIngredient(ingredients.get(1));
            }

            pizzas.get(0).addIngredient(ingredients.get(2));pizzas.get(0).addIngredient(ingredients.get(3));pizzas.get(0).addIngredient(ingredients.get(7));pizzas.get(0).addIngredient(ingredients.get(12));pizzas.get(0).addIngredient(ingredients.get(18));

            pizzas.get(1).addIngredient(ingredients.get(2));

            pizzas.get(2).addIngredient(ingredients.get(3));pizzas.get(2).addIngredient(ingredients.get(4));pizzas.get(2).addIngredient(ingredients.get(18));

            pizzas.get(3).addIngredient(ingredients.get(8)); pizzas.get(3).addIngredient(ingredients.get(13)); pizzas.get(3).addIngredient(ingredients.get(14));

            pizzas.get(5).addIngredient(ingredients.get(15));

            pizzas.get(6).addIngredient(ingredients.get(17)); pizzas.get(6).addIngredient(ingredients.get(11)); pizzas.get(6).addIngredient(ingredients.get(19)); pizzas.get(6).addIngredient(ingredients.get(4));

            pizzas.get(7).addIngredient(ingredients.get(10));

            pizzas.get(8).addIngredient(ingredients.get(4)); pizzas.get(8).addIngredient(ingredients.get(20));

            pizzas.get(9).addIngredient(ingredients.get(21));pizzas.get(9).addIngredient(ingredients.get(10));

            pizzas.get(10).addIngredient(ingredients.get(22)); pizzas.get(10).addIngredient(ingredients.get(9));

            pizzas.get(11).addIngredient(ingredients.get(2)); pizzas.get(11).addIngredient(ingredients.get(7));pizzas.get(11).addIngredient(ingredients.get(3));

            pizzas.get(12).addIngredient(ingredients.get(4)); pizzas.get(12).addIngredient(ingredients.get(5)); pizzas.get(12).addIngredient(ingredients.get(16));

            pizzas.get(13).addIngredient(ingredients.get(6)); pizzas.get(13).addIngredient(ingredients.get(2));

            pizzas.get(14).addIngredient(ingredients.get(9));

            this.pizzaRepository.saveAll(pizzas);
            this.orderRepository.saveAll(orders);
        }
    }
}
