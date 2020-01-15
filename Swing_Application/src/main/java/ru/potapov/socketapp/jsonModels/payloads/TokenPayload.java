package ru.potapov.socketapp.jsonModels.payloads;

import ru.potapov.socketapp.jsonModels.Payload;

public class TokenPayload extends Payload {
    private String token;

    public String getToken() {
        return token;
    }

    public TokenPayload setToken(String token) {
        this.token = token;
        return this;
    }
}
