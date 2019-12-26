package ru.potapov.Swing.fractal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

class MDraw extends JPanel {
    private static int maxIter = 180;
    private double[] scala = {1, 1};
    private double[] posizione = {0, 0};


    public double[] getPosizione() {
        return posizione;
    }

    public void setPosizione(double[] posizione) {
        this.posizione = posizione;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Dimension d = getSize();
        int x;
        int y;
        int iterazioni;
        double reale = 0, immaginaria = 0;
        double cr, ci;
        double tr = 0, ti = 0;
        double mod = 0;

        float mu = 0;
        BufferedImage I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (x = 0; x < d.width; x++) {
            for (y = 0; y < d.height; y++) {
                cr = ((double) x - posizione[0]) / scala[0];
                ci = -((double) y - posizione[1]) / scala[1];

                reale = 0;
                immaginaria = 0;
                for (iterazioni = 0; iterazioni < maxIter; iterazioni++) {

                    tr = reale * reale - immaginaria * immaginaria + cr;
                    ti = 2 * reale * immaginaria + ci;
                    reale = tr;
                    immaginaria = ti;
                    mod = reale * reale + immaginaria * immaginaria;

                    if (mod > 4)
                        break;
                }


                iterazioni = (int) (maxIter * Math.sqrt((double) iterazioni / maxIter));
                mu = (float) (iterazioni - (Math.log(Math.log(Math.sqrt(mod)))) / Math.log(4.0));
                I.setRGB(x, y, (int) mu);

            }

        }
        g.drawImage(I, 0, 0, this);

    }

    public double[] getScala() {
        return scala;
    }

    public void setScala(double[] scala) {
        this.scala = scala;
    }
}

class Finestra extends JFrame {
    double startX = 0;
    double startY = 0;

    public Finestra() {
        this.setLayout(new BorderLayout());
        this.setBounds(300, 300, 300, 300);
        final MDraw mandelbrotdraw = new MDraw();

        final double[] arrPos = new double[2];
        Dimension d = getSize();
        arrPos[0] = d.width;
        arrPos[1] = d.height ;
        mandelbrotdraw.setPosizione(arrPos);
        repaint();
        this.add(mandelbrotdraw, BorderLayout.CENTER);

        MouseWheelListener mW = new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent arg0) {
                int scroll = arg0.getWheelRotation();
                double[] scala = mandelbrotdraw.getScala();
                scala[0] += scroll;
                scala[1] += scroll;
                mandelbrotdraw.setScala(scala);
                repaint();
            }

        };

        MouseInputAdapter mI = new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                startX = p.x;
                startY = p.y;

            }

            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                int curX = p.x;
                int curY = p.y;

                arrPos[0] += (curX - startX);
                arrPos[1] += (curY - startY);

                mandelbrotdraw.setPosizione(arrPos);
                repaint();

                startX = curX;
                startY = curY;
            }
        };
        this.addMouseListener(mI);
        this.addMouseMotionListener(mI);
        this.addMouseWheelListener(mW);

        ActionListener tim = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                double[] scala = mandelbrotdraw.getScala();
                scala[0] += 3;
                scala[1] += 3;
                mandelbrotdraw.setScala(scala);
                repaint();
            }

        };

        Timer t = new Timer(1, tim);
        t.start();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


public class Mandelbrot {
    public static void main(String[] args) {
        Finestra m = new Finestra();
    }
}