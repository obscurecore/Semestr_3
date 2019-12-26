package ru.potapov.MVC.model;


import ru.potapov.MVC.Bean.StudentB;

public class MyModel {
public StudentB getStudent(){
   //some code that receive my student from BD(for example)
        return  new StudentB();
    }
}