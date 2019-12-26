package ru.potapov.socketapp.dao;

import ru.potapov.socketapp.models.User;
import ru.potapov.socketapp.utilities.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {
    private Connection connection = ConnectionJDBC.getConnection();

    private final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM \"user\"WHERE login=?";

    public Optional<User> findUserByLogin(String login) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.ofNullable(rowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }




    private RowMapper<User> rowMapper = row -> {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setLogin(row.getString("login"));
        user.setPassword(row.getString("password"));
        user.setRoleId(row.getInt("id_role"));
        return user;
    };
}
