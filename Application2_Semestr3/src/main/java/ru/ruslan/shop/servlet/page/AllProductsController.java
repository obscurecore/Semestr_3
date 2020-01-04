package ru.ruslan.shop.servlet.page;


import ru.ruslan.shop.Constants;
import ru.ruslan.shop.entity.Product;
import ru.ruslan.shop.servlet.AbstractController;
import ru.ruslan.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Potapov Ruslan
 *
 *
 * receive data from busienss service as collcetion and transfer data to view (template engine)
 */
@WebServlet("/products")
public class  AllProductsController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;
//idea : controller get from BD data (for ex - list of all products with some class
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		List<?> products =getBusinessService().getProducts ();
		req.setAttribute("products", products);
		*/
		List<Product> products = getProductService().listAllProducts(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
		req.setAttribute("products", products);
		int totalCount = getProductService().countAllProducts();
	//	req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_HTML_PAGE));
		RoutingUtils.forwardToPage("products.jsp", req, resp);
	}
}
