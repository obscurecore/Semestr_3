package ru.potapov.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Actions {
    static JFrame jFrame = getframe();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) {
        jFrame.add(jPanel);
        JButton jButton = new JButton(new MyActions());
        jButton.setText("submit");
        jPanel.add(jButton);
    }


    static class MyActions extends AbstractAction {

        MyActions(){
            putValue(AbstractAction.SHORT_DESCRIPTION,"My small action");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            jPanel.setBackground(Color.BLUE);
        }
    }

    static JFrame getframe() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setBounds(750, 250, 500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }


}
