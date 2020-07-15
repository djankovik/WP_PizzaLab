package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="show-pizza-servlet", urlPatterns = "")
public class ShowPizzaServlet extends HttpServlet {

    private final PizzaService pizzaService;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizzaServlet(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine){
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());
        List<Pizza> pizzas = pizzaService.listAllPizzas();
        webContext.setVariable("pizzas",pizzas);

        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("thymeleaf-listpizzas.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pizzaName = req.getParameter("pizzaSelected");
        String clientName = (String) session.getAttribute("username");
        String clientAddress = (String) session.getAttribute("address");
        Order order = new Order();
        order.setPizzaType(pizzaName);
        order.setClientName(clientName);
        order.setClientAddress(clientAddress);
        session.setAttribute("order", order);
        resp.sendRedirect("/PizzaOrder.do");
    }
}
