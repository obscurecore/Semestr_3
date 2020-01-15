package ru.potapov.Swing;

import javax.swing.*;

public class EventHandler {
    public static void main(String[] args) {
        JFrame jFrame = getframe();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        JButton jButton = new JButton("submit");
        jPanel.add(jButton);
//            jButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    jFrame.setTitle(((JButton)e.getSource()).getText());//берем у обьекта текст и засовываем в тайтл
//                }

//            });
     //   jButton.addActionListener(EventHandler.create(ActionListener.class, jFrame, "title", "source.text"));
        // над чем делаем, свойство
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



