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
 *
 * productCount shows alert how much items will be founded
 */
@WebServlet("/search")
public class SearchController extends AbstractController {
	private static final long serialVersionUID = 1015660808630879774L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
		SearchForm searchForm = createSearchForm(req);
		List<Product> products = getProductService().listProductsBySearchForm(searchForm, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
		req.setAttribute("products", products);
		int totalCount = getProductService().countProductsBySearchForm(searchForm);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_HTML_PAGE));
		req.setAttribute("searchForm", searchForm);*/
		req.setAttribute("productCount", 24);

		RoutingUtils.forwardToPage("search-result.jsp", req, resp);
	}
}
