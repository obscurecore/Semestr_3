package ru.ruslan.shop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Potapov Ruslan
 */
public interface ResultSetHandler<T> {

	T handle(ResultSet rs) throws SQLException;
}
