package ru.potapov.socketapp.jsonModels;

import java.util.List;

public class Data<T>{
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public Data<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
