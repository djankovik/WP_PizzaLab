package mk.finki.ukim.mk.lab.web.listeners;

import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class CustomListener implements HttpSessionListener, ServletRequestListener, ServletContextListener {
    //HttpSessionListener
    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("[WP-Log] HttpSessionListener: Session created. Id: "+event.getSession().getId().toString());
    }
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println("[WP-Log] HttpSessionListener: Session destroyed. Id: "+event.getSession().getId().toString());
    }

    //ServletRequestListener
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println("[WP-Log] ServletRequestListener: Request destroyed. Request sent to: "+ event.getServletRequest().getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent event) {
        System.out.println("[WP-Log] ServletRequestListener: Request initializing. Request sent to: "  + event.getServletRequest().getRemoteAddr());
    }

    //ServletContextListener
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("[WP-Log] ServletContextListener: Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("[WP-Log] ServletContextListener: Context destroyed");
    }
}