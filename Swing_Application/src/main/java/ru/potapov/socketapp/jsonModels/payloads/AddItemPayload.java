package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class AddItemPayload extends Payload {
    private String goodName;
    private Integer price;

    public String getGoodName() {
        return goodName;
    }

    public AddItemPayload setGoodName(String goodName) {
        this.goodName = goodName;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AddItemPayload setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
