package ru.ruslan.shop.service.impl;


import ru.ruslan.shop.service.BusinessService;
import ru.ruslan.shop.service.OrderService;
import ru.ruslan.shop.service.ProductService;

import javax.servlet.ServletContext;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Potapov Ruslan
 * <p>
 * <p>
 * access to the b.service with settings must exist as single instance,
 * (all init here) and can get any instnaces in any controller
 * so that object store in attribute of ServletContext
 */
public class ServiceManager {
    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public String getApplicationProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    private Properties applicationProperties = new Properties();

    public void close() {//close resources
    }

    //private BusinessService businessService;
    private ProductService productService;
    private OrderService orderService;

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*/ public BusinessService getBusinessService(){
          return businessService;
      }*/
    public ProductService getBusinessService() {
        return productService;
    }

    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();

    }


}
