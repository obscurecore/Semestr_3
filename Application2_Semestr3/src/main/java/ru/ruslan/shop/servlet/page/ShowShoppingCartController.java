package ru.ruslan.shop.servlet.page;


import ru.ruslan.shop.servlet.AbstractController;
import ru.ruslan.shop.util.RoutingUtils;
import ru.ruslan.shop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author Potapov Ruslan
 */
@WebServlet("/shopping-cart")
public class ShowShoppingCartController extends AbstractController {
	private static final long serialVersionUID = -1916373553298888514L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentShoppingCartCreated(req)) {
			RoutingUtils.forwardToPage("shopping-cart.jsp", req, resp);
		} else {
			RoutingUtils.redirect("/products", req, resp);
		}
	}
}
