package ru.potapov.socketapp.jsonModels;

public class JsonObject {

    private String header;
    private Payload payload;
    private String token;

    @Override
    public String toString() {
        return "JsonObject{" +
                "header='" + header + '\'' +
                ", payload=" + payload +
                ", token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
