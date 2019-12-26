package ru.ruslan.shop.servlet.page;


import ru.ruslan.shop.servlet.AbstractController;
import ru.ruslan.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author Potapov Ruslan
 */
@WebServlet("/error")
public class ErrorController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("statusCode", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		RoutingUtils.forwardToPage("error.jsp", req, resp);
	}
}
