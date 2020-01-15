package ru.ruslan.shop.servlet.ajax;



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
@WebServlet("/ajax/html/more/search")
public class SearchResultsMoreController extends AbstractController {
	private static final long serialVersionUID = -2651974520717714088L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	SearchForm searchForm = createSearchForm(req);
	//	List<Product> products = getProductService().listProductsBySearchForm(searchForm, getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE);
	//	req.setAttribute("products", products);
		RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
	}
}
