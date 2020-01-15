package ru.potapov.Swing;

import javax.swing.*;

public class Frame extends JFrame {
     Frame(){
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setSize(400, 400);
          setUndecorated(true);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setExtendedState(JFrame.EXIT_ON_CLOSE);
          setVisible(true);
     }
     public static void main(String[] args){
          new Frame();
     }
}