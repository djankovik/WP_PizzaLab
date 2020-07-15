package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.PizzaSize;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/controller")
public class PizzaController {

    @Autowired
    private HttpServletRequest context;

    private final PizzaService pizzaService;
    private final OrderService orderService;

    public PizzaController(PizzaService pizzaService, OrderService orderService) {
        this.pizzaService = pizzaService;
        this.orderService = orderService;
    }

    @GetMapping("/menu")
    public ModelAndView showMenu() {
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("pizzas",pizzaService.listAllPizzas());
        modelAndView.addObject("bodyContent", "partial-pizza-menu");
        return modelAndView;
    }

    @GetMapping("/pizzaList")
    public ModelAndView showPizzaList() {
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("pizzas",pizzaService.listAllPizzas());
        modelAndView.addObject("bodyContent", "partial-list-pizzas");
        return modelAndView;
    }

    @PostMapping("/pizzaList")
    public String setPizza(@RequestParam(value = "pizzaSelected",required = false) String pizzaSelected) {
        HttpSession session = context.getSession();
        Order o=new Order();
        o.setPizzaType(pizzaSelected);
        o.setClientAddress((String) session.getAttribute("address"));
        o.setClientName((String) session.getAttribute("username"));
        session.setAttribute("order",o);
        session.setAttribute("pizzaSelected",pizzaSelected);
        return "redirect:pizzaSize";
    }

    @GetMapping("/pizzaSize")
    public ModelAndView selectPizzaSize(HttpServletRequest request) {
        HttpSession session = context.getSession();
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("pizzaSizes", PizzaSize.values());
        modelAndView.addObject("pizzaSelected", session.getAttribute("pizzaSelected"));
        modelAndView.addObject("bodyContent", "partial-pizza-size");
        return modelAndView;
    }

    @PostMapping("/pizzaSize")
    public String setPizzaSize(@RequestParam(value="sizeSelected",required = false) String sizeSelected) {
        HttpSession session = context.getSession();
        Order o= (Order) session.getAttribute("order");
        o.setPizzaSize(sizeSelected);
        session.setAttribute("order",o);
        return "redirect:confirmationInfo";
    }

    @GetMapping("/confirmationInfo")
    public ModelAndView showConfirmationInfo() {
        HttpSession session = context.getSession();
        ModelAndView modelAndView = new ModelAndView("master-template");

        String userAgent = context.getHeader("User-Agent");
        String ipaddress = context.getRemoteHost();
        Order order = (Order) session.getAttribute("order");

        modelAndView.addObject("userAgent",userAgent);
        modelAndView.addObject("ipAddress",ipaddress);
        modelAndView.addObject("order", order);
        modelAndView.addObject("bodyContent", "partial-confirmation-info");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logOut() {
        context.getSession().invalidate();
        return "redirect:login";
    }

    @GetMapping("/login")
    public ModelAndView showLogin() {
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("bodyContent", "partial-log-in");
        return modelAndView;
    }
    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,@RequestParam("address") String address) {
        HttpSession session = context.getSession();
        session.setAttribute("username", username);
        session.setAttribute("address", address);
        return "redirect:pizzaList";
    }

    @RequestMapping(value = "/pizzas/new", method = RequestMethod.GET)
    public ModelAndView showCreatePizza() {
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("bodyContent", "partial-create-pizza");
        return modelAndView;
    }


    @PostMapping("/pizzas/create")
    public String createPizza(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam(defaultValue = "false") boolean veggie,
                              @RequestParam List<String> ingredients) {
        this.pizzaService.createPizza(name,description,veggie,ingredients);
        return "redirect:/pizzaList";
    }

    @GetMapping("/pizzas/{name}")
    public ModelAndView showEditPizza(@PathVariable String name) {
        Pizza pizza = this.pizzaService.findByName(name);

        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("bodyContent", "partial-edit-pizza");
        modelAndView.addObject("pizza", pizza);
        return modelAndView;
    }

    @PostMapping("/pizzas/{name}")
    public String updatePizza(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam boolean veggie,
                              @RequestParam List<String> ingredients) {
        this.pizzaService.updatePizza(name,description,veggie,ingredients);
        return "redirect:/pizzaList";

    }

    @PostMapping("/pizzas/{name}/delete")
    public String deletePizza(@PathVariable String name) {
        this.pizzaService.deletePizza(name);
        return "redirect:/pizzaList";
    }

}
