package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.PizzaSize;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="select-pizza-size-servlet",urlPatterns = "/PizzaOrder.do")
public class SelectPizzaSizeServlet extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public SelectPizzaSizeServlet(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());

        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        String selectedPizza = order.getPizzaType();
        webContext.setVariable("selectedPizzaName",selectedPizza);

        PizzaSize[] sizes = PizzaSize.values();
        webContext.setVariable("pizzaSizes",sizes);
        resp.setContentType("text/html; charset=UTF-8");

        this.springTemplateEngine.process("thymeleaf-selectPizzaSize.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pizzaSize = req.getParameter("sizeSelected");
        Order order = (Order) session.getAttribute("order");
        order.setPizzaSize(pizzaSize);
        session.setAttribute("order", order);
        orderService.placeOrder(order);
        resp.sendRedirect("/ConfirmationInfo.do");
    }
}
