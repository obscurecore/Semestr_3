package ru.potapov.socketapp.services;

import ru.potapov.socketapp.dao.OrderHistoryDAO;

public class OrderHistoryService {
    public boolean buy(Integer id_user, Integer id_good) {
        return new OrderHistoryDAO().insert(id_user, id_good);
    }
}
