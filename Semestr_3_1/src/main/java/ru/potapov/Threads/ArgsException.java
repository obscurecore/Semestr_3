package ru.potapov.Threads;

public class ArgsException extends Exception {
    private int number;
    public int getNumber(){return number;}
    public ArgsException(String message, int num){

        super(message);
        number=num;
    }
}
