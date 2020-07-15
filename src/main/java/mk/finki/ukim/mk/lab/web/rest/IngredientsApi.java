package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientsApi {
    private final IngredientService ingredientService;

    public IngredientsApi(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam("name") String name,
                                       @RequestParam("spicy") boolean spicy,
                                       @RequestParam("amount") float amount,
                                       @RequestParam(value="veggie", defaultValue = "false") boolean veggie) {
        return ingredientService.createIngredient(name,spicy,amount,veggie);
    }

    @PatchMapping("/{name}")
    public Ingredient updateIngredient(@PathVariable String name,
                                       @RequestParam("spicy") boolean spicy,
                                       @RequestParam("amount") float amount,
                                       @RequestParam(value="veggie", defaultValue = "false") boolean veggie) {
        return ingredientService.updateIngredient(name,spicy,amount,veggie);
    }

    @DeleteMapping("/{name}")
    public void deleteIngredient(@PathVariable String name) {
        ingredientService.deleteIngredient(name);
    }

    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        return ingredientService.getAllIngredients(page, size);
    }
    @GetMapping("/all")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.listAllIngredients();
    }

    @GetMapping("/{name}")
    public Ingredient getIngredient(@PathVariable String name) {
        return ingredientService.findByName(name);
    }

    @GetMapping(params = "spicy")
    public List<Ingredient> getAllSpicyIngredients(@RequestParam boolean spicy) {
        return ingredientService.getAllSpicyIngredients(spicy);
    }

    @GetMapping("/{name}/pizzas")
    public List<Pizza> getPizzasWithIngredient(@PathVariable String name) {
        return ingredientService.getPizzasWithIngredient(name);
    }

}
