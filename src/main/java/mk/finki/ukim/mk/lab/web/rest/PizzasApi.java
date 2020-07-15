package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzasApi {

    private final PizzaService pizzaService;

    public PizzasApi(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam(value="veggie", defaultValue = "false") boolean veggie,
                             @RequestParam List<String> ingredients) {
        Pizza createdPizza = pizzaService.createPizza(name,description,veggie,ingredients);

        return createdPizza;
    }

    @PostMapping("/backend")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizzaValidateBackend(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam List<String> ingredients) {
        Pizza createdPizza = pizzaService.createPizzaWithValidation(name,description,ingredients);

        return createdPizza;
    }

    @PutMapping("/{name}")
    public Pizza updatePizza(@PathVariable String name,
                             @RequestParam("description") String description,
                             @RequestParam(value="veggie", defaultValue = "false") boolean veggie,
                             @RequestParam List<String> ingredients) {
        return pizzaService.updatePizza(name,description,veggie,ingredients);
    }

    @PutMapping("/backend/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza updatePizzaValidateBackend(@PathVariable String name,
                                            @RequestParam("description") String description,
                                            @RequestParam List<String> ingredients) {
        Pizza createdPizza = pizzaService.updatePizzaWithValidation(name,description,ingredients);

        return createdPizza;
    }

    @DeleteMapping("/{name}")
    public void deletePizza(@PathVariable String name) {
        pizzaService.deletePizza(name);
    }

    @GetMapping
    public Page<Pizza> getAllPizzas(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        return pizzaService.getAllPizzas(page, size);
    }

    @GetMapping("/{name}")
    public Pizza getPizza(@PathVariable String name) {
        return pizzaService.findByName(name);
    }

    @GetMapping(params = "totalIngredients")
    public List<Pizza> getPizzasWithIngredientsLessThan(@QueryParam("totalIngredients") int totalIngredients) {
        return pizzaService.getPizzasWithIngredientsLessThan(totalIngredients);
    }

    @GetMapping("/compare")
    public List<Ingredient> getCommonIngredients(@QueryParam("pizza1") String pizza1,@QueryParam("pizza2") String pizza2) {
        return pizzaService.getCommonIngredients(pizza1,pizza2);
    }

    @GetMapping(params = "spicy")
    public List<Pizza> getAllSpicyIngredients(@QueryParam("spicy") boolean spicy) {
        return pizzaService.getAllSpicyPizzas(spicy);
    }

}
