package ru.ruslan.shop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.shop.service.impl.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Potaopv Ruslan
 *
 * access from litener application
 * and as soon as application will be inited it call ServiceManager,
 * where created all needed instances(B.service and e.t.c)
 *
 *
 *
 */
@WebListener
public class ShopApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopApplicationListener.class);
    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
        } catch (Exception e) {
            LOGGER.error("Web app init failed"+e.getMessage(), e);
            throw e;

        }

        LOGGER.info("Web application 'shop' initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web application 'shop' destroyed");
    }
}
