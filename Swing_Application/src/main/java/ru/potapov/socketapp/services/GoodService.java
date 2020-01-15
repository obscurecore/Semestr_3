package ru.potapov.socketapp.services;

import ru.potapov.socketapp.dao.ItemDAO;
import ru.potapov.socketapp.jsonModels.Data;
import ru.potapov.socketapp.models.Item;

import java.util.List;

public class GoodService {

    public Data<Item> showAll() {
        List<Item> items = new ItemDAO().getAll();
        /*if (goods.size() > 0) {
            String all = "================================================\nid      name        price\n";

            for (Good good : goods) {
                all = good.getId() + "     " + good.getName() + "      " + good.getPrice() + "\n";
            }
            all += "================================================";
            return all;
        }
        return "================================================\n NOTHING TO SHOW \n ================================================";*/
        return new Data<Item>().setData(items);
    }

    public boolean addGood(String name, Integer price) {
        return new ItemDAO().insert(new Item().setName(name).setPrice(price));
    }

    public boolean deleteGood(Integer id_good) {
        return new ItemDAO().deleteById(id_good);
    }
}
