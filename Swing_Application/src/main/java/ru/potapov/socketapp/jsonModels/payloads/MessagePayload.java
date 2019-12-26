package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class MessagePayload extends Payload {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
