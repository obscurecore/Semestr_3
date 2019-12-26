package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class LogoutPayload extends Payload {
    private String message = "?";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
