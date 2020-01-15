package ru.ruslan.shop.servlet.page;


import ru.ruslan.shop.Constants;
import ru.ruslan.shop.servlet.AbstractController;
import ru.ruslan.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Potapov Ruslan
 */
@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController {
	private static final long serialVersionUID = 1015660808630879774L;
	private static final int SUBSTRING_INDEX = "/products".length();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		/*List<Product> products = getProductService().listProductsByCategory(categoryUrl, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
		req.setAttribute("products", products);
		int totalCount = getProductService().countProductsByCategory(categoryUrl);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_HTML_PAGE));
		req.setAttribute("selectedCategoryUrl", categoryUrl);*/
		RoutingUtils.forwardToPage("products.jsp", req, resp);
	}
}
