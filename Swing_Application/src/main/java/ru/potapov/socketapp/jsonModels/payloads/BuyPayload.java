package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class BuyPayload extends Payload {
    private Integer id_good;

    public Integer getId_good() {
        return id_good;
    }

    public BuyPayload setId_good(Integer id_good) {
        this.id_good = id_good;
        return this;
    }
}
