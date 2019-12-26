package ru.ruslan.shop.exception;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Potapov Ruslan
 */

public class ValidationException extends AbstractApplicationException {
	private static final long serialVersionUID = -6843925636139273536L;

	public ValidationException(String s) {
		super(s, HttpServletResponse.SC_BAD_REQUEST);
	}
}
