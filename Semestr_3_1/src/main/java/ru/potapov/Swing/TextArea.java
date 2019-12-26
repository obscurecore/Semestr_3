package ru.potapov.Swing;

import javax.swing.*;

public class TextArea {
    static JFrame jFrame = getframe();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) {

        jFrame.add(jPanel);
        JLabel jLabel = new JLabel("THIS is LABAEL");

        jPanel.add(new JTextField(20));
        JTextField jTextField = new JTextField();
        // jTextField.setText();
        //jTextField.getText();
        JTextArea jTextArea = new JTextArea(5, 20);
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane);
        jPanel.revalidate();//redraw
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

