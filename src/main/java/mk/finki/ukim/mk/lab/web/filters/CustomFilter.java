package mk.finki.ukim.mk.lab.web.filters;

import mk.finki.ukim.mk.lab.model.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter
//@org.springframework.core.annotation.Order(1)
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();

        String name = (String) session.getAttribute("username");
        String address = (String) session.getAttribute("address");
        Order order;
        order = (Order) session.getAttribute("order");
        String pizzaType=null,pizzaSize=null;
        if (order != null){
            pizzaType = order.getPizzaType();
            pizzaSize = order.getPizzaSize();
        }
        String path = httpRequest.getServletPath();
        if(path.endsWith("h2")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(path.contains("api")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(!path.startsWith("/controller")){
        if("/logout".equals(path)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (!"/login".equals(path) && (name == null || name.isEmpty() || address == null || address.isEmpty())) {
            httpResp.sendRedirect("/login");
        }
        else if("/PizzaOrder.do".equals(path) && (pizzaType == null || pizzaType.isEmpty())){
            httpResp.sendRedirect("");
        }
        else if("/ConfirmationInfo.do".equals(path)) {
            if (pizzaType == null || pizzaType.isEmpty()) {
                httpResp.sendRedirect("");
            }
            else if (pizzaSize == null || pizzaSize.isEmpty()) {
                httpResp.sendRedirect("/PizzaOrder.do");
            }
            else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        }else{
            if("/controller/menu".equals(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
           else if("/controller/logout".equals(path)){
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else if (!"/controller/login".equals(path) && (name == null || name.isEmpty() || address == null || address.isEmpty())) {
                httpResp.sendRedirect("/controller/login");
            }
            else if("/controller/pizzaSize".equals(path) && (pizzaType == null || pizzaType.isEmpty())){
                httpResp.sendRedirect("/controller/pizzaList");
            }
            else if("/controller/confirmationInfo".equals(path)) {
                if (pizzaType == null || pizzaType.isEmpty()) {
                    httpResp.sendRedirect("/controller/pizzaList");
                } else if (pizzaSize == null || pizzaSize.isEmpty()) {
                    httpResp.sendRedirect("/controller/pizzaSize");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
