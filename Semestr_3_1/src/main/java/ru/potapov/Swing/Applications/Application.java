package ru.potapov.Swing.Applications;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Application {
    static JPanel jPanel = new JPanel();

    private JFrame frame;
    private static JPanel containerPane;
    private static JPanel topPane;
    private JPanel bottomPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application().createAndShowGui();
            }
        });
    }

    public void createAndShowGui() {

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





        frame = new JFrame("Example of 2 panels");
        containerPane = new JPanel();
        topPane = new JPanel();
        bottomPane = new JPanel();

        containerPane.setLayout(new GridLayout(0, 2));
        topPane.setLayout(new GridLayout(0, 2, 5, 2));
        bottomPane.setLayout(new BorderLayout());
//

        JButton button = null;
        for (int i = 1; i <= 50; i++) {
            topPane.add(button = new JButton(" Press " + i));
        }

        JButton HeH = new JButton(new MyActions());
        HeH.setText("West");

        JButton hEh = new JButton(new MyActions());
        hEh.setText("East");
        bottomPane.add(HeH, BorderLayout.WEST);
        bottomPane.add(hEh, BorderLayout.EAST);

        topPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), " GridLayout"));
        bottomPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), " BorderLayout"));

        containerPane.add(new JScrollPane(topPane));

        containerPane.add(bottomPane);
        frame.add(jPanel);
        frame.setJMenuBar(jMenuBar);
        frame.add(containerPane);
        frame.add(new JLabel("STATUS : "),BorderLayout.SOUTH);
//      frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.revalidate();

    }
    static class MyActions extends AbstractAction {

        MyActions(){
            putValue(AbstractAction.SHORT_DESCRIPTION,"My small action");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            topPane.setBackground(Color.BLUE);
        }
    }
}