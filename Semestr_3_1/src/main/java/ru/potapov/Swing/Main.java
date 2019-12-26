package ru.potapov.Swing;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Bitstream") {
        };
/*
1 тип шрифта
2 Bold,italic
3 size
 */



        //Font font = new Font();
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // jFrame.setSize(500,300);
        // jFrame.setLocation(500,200);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 250, 500, 500);//from top left corner
        jFrame.add(new Mycomponent());//компонет где можно рисовать или выполнять определнные манипуляции

        //jFrame.setBounds(500,0,500,300);//from top left corner
        jFrame.setTitle("test");

    }
    static class Mycomponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {

            Font font = new Font("Bitstream",Font.BOLD,20);
            Graphics g2 = (Graphics2D)g;
            g2.setFont(font);
            g2.drawString("hello world",20,20);//координаты
        }
    }
}
