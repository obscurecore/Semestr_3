package ru.potapov.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionListener {
    public static void main(String[] args) {
        JFrame jFrame = getframe();
        JPanel jPanel= new JPanel();
        jFrame.add(jPanel);
        JButton jButton = new JButton("Submit");
        jPanel.add(jButton);
        jButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jPanel.setBackground(Color.BLUE);
            }
        });
//можно написать листенер в отдельном классе, но тогда не будет доступа к jframe
    }
    static  JFrame getframe(){
        JFrame jFrame = new JFrame(){};
        jFrame.setVisible(true);
        jFrame.setBounds(750,250,500,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return  jFrame;
    }
}

