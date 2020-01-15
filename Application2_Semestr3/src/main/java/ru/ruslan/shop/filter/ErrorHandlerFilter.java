package ru.ruslan.shop.filter;




import ru.ruslan.shop.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 
 * @author Potapov Ruslan
 */
@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {
	private static final String INTERNAL_ERROR = "Internal error";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

/*	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(servletRequest,servletResponse);
	} catch (Throwable th) {
			String requestUrl = ((HttpServletRequest)servletRequest).getRequestURI();
			//Logger.error("Reguest"+requestUrl+"failed"+th.getMessage(), th);
			RoutingUtils.forwardToPage("error.jsp", ((HttpServletRequest)servletRequest),((HttpServletResponse)servletResponse));
		}

	}
*/
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(request,response);
		} catch (Throwable th) {
			String requestUrl = ((HttpServletRequest)request).getRequestURI();
			LOGGER.error("Reguest"+requestUrl+"failed"+th.getMessage(), th);
			RoutingUtils.forwardToPage("error.jsp", ((HttpServletRequest)request),((HttpServletResponse)response));
		}
	}

	@Override
	public void destroy() {

	}
}
