package ru.potapov.socketapp.dao;

import ru.potapov.socketapp.models.Item;
import ru.potapov.socketapp.utilities.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection = ConnectionJDBC.getConnection();
    private final String GET_ALL = "SELECT * FROM item";
    private final String INSERT = "INSERT INTO item (name, price) VALUES (?,?)";
    private final String DELETE_BY_ID = "DELETE FROM item WHERE id = ?";

    public boolean insert(Item item) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    items.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private RowMapper<Item> rowMapper = row ->
            new Item().setId(row.getInt("id"))
                    .setName(row.getString("name"))
                    .setPrice(row.getInt("price"));
}
