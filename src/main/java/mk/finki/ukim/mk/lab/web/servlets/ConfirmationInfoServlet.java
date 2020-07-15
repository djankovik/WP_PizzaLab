package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.PizzaSize;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="confirmation-info-servlet",urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfoServlet extends HttpServlet
{
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext webContext = new WebContext(req,resp,req.getServletContext());

        HttpSession session = req.getSession();
        String userAgent = req.getHeader("User-Agent");
        String ipaddress = req.getRemoteHost();
        Order order = (Order) session.getAttribute("order");
        webContext.setVariable("order",order);
        webContext.setVariable("userAgent",userAgent);
        webContext.setVariable("ipAddress",ipaddress);
        resp.setContentType("text/html; charset=UTF-8");

        this.springTemplateEngine.process("thymeleaf-confirmationInfo.html", webContext, resp.getWriter());
    }
}
