package ru.ruslan.shop.service.impl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {
    /**
     * In listener has appList an as soon as application will be created the app listener will be done(contextIntialized)
     *
     * init when application has run and occur event sce, where registered listeners will be called
     *
     */
    private ServiceManager serviceManager;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        serviceManager= ServiceManager.getInstance(sce.getServletContext());
       // serviceManager.getBusinessService().doSomething();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
       // serviceManager.getBusinessService().doSomething();
        serviceManager.close();
    }
}
