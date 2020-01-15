package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class ShowAllPayload extends Payload {
    private String some_string = "?";

    public String getSome_string() {
        return some_string;
    }

    public void setSome_string(String some_string) {
        this.some_string = some_string;
    }
}
