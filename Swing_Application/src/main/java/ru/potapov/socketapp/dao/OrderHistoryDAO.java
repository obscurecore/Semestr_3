package ru.potapov.socketapp.dao;

import ru.potapov.socketapp.utilities.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderHistoryDAO {
    private Connection connection = ConnectionJDBC.getConnection();
    private final String INSERT = "INSERT INTO order_his (id_user, id_good, date) VALUES (?,?,NOW())";

    public boolean insert(Integer id_user, Integer id_good) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, id_user);
            ps.setInt(2, id_good);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
