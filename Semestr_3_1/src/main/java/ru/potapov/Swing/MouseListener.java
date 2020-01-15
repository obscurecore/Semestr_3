package ru.potapov.Swing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener {
    static JFrame jFrame = getframe();

    public static void main(String[] args) {
        JComponent jComponent = new MyComponent();

        jFrame.add(jComponent);
        jFrame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseMoved(mouseEvent);
                MyComponent.xCord = mouseEvent.getX();
                jComponent.repaint();
            }
        });




    /* jFrame.addMouseListener(new MouseAdapter() {
        @Override
         public void mouseClicked(MouseEvent mouseEvent) {
             super.mouseClicked(mouseEvent);
             jPanel.setBackground(Color.BLUE);
         }
     });*/
    }

    static class MyComponent extends JComponent {
        public static int xCord;
        public static int yCord;

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            ((Graphics2D) graphics).drawString("Coordinates x:" + xCord+" y "+yCord, 50, 50);

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
