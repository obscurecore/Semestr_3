package ru.potapov.Swing;

import javax.swing.*;
import javax.swing.KeyStroke;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    static JFrame jframe = getFrame();
    static JPanel jPanel = new JPanel();
    static  JPanel jPanel1 = new JPanel();

    public static void main(String[] args) {
        jframe.add(jPanel);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("edit");
        jMenuBar.add(file);
        jMenuBar.add(edit);

        file.add((new JMenuItem("Open", '0')));
        JMenuItem save = file.add(new JMenuItem("Save"));
        save.setEnabled(false);
        file.addSeparator();
        JMenuItem exit = file.add(new JMenuItem("Exit"));
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctr e"));
        edit.add(new JMenuItem("Cut"));
        edit.add(new JMenuItem("Copy"));
        JMenu options = new JMenu("Options");
        edit.add(options);
        options.add("one");
        options.add("two");

        JToolBar jToolBar= new JToolBar("title");
        JButton jButton = new JButton("one");
        jButton.setToolTipText("click");
        jToolBar.add(jButton);
        jToolBar.addSeparator();
        jToolBar.add(new JButton("second"));


//jframe.add(new JButton("one"), BorderLayout.NORTH);
jframe.add(jPanel.add(new JButton("one")),BorderLayout.EAST);

        jframe.add(jPanel.add(new JButton("one")),BorderLayout.WEST);

   //     jframe.add(new JButton("one"), BorderLayout.EAST);
     //   jframe.add(new JButton("one"), BorderLayout.CENTER);



//        jPanel.add(new JButton("one"));
//        jPanel.add(new JButton("one"));
//        jPanel.add(new JButton("one"));

        jPanel.add(jToolBar);
        jframe.setJMenuBar(jMenuBar);
        jframe.revalidate();

    }

    private static JFrame getFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setBounds(750, 250, 500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }

}
