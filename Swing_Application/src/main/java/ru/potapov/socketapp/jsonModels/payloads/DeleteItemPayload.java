package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class DeleteItemPayload extends Payload {
    public Integer getId_good() {
        return id_good;
    }

    public DeleteItemPayload setId_good(Integer id_good) {
        this.id_good = id_good;
        return this;
    }

    private Integer id_good;
}
