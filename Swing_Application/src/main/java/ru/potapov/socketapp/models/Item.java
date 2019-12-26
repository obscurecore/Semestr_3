package ru.potapov.socketapp.models;

public class Item {
    private Integer id;
    private String name;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public Item setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Item setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
