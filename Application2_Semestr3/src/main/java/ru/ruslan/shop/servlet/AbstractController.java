package ru.ruslan.shop.servlet;


import ru.ruslan.shop.service.BusinessService;
import ru.ruslan.shop.service.OrderService;
import ru.ruslan.shop.service.ProductService;
import ru.ruslan.shop.service.impl.ServiceManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Potapov Ruslan
 */
public abstract class AbstractController extends HttpServlet {
    private static final long serialVersionUID = -2031074947573473708L;
    //private BusinessService businessService;
    private ProductService productService;
    private OrderService orderService;

    //to parents won't override
    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();


        //	businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
    }

    public final ProductService getProductService() {
        return productService;
    }


    public final OrderService getOrderService() {
        return orderService;
    }



	/*public final BusinessService getBusinessService(){
		return businessService;
	}*/
}
