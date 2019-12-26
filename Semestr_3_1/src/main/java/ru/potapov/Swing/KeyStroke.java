package ru.potapov.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeyStroke {
    static JFrame jFrame = getframe();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) {
        AbstractAction myAction = new MyActions();
        jFrame.add(jPanel);
        JButton jButton = new JButton(new MyActions());
        jButton.setText("submit");
        jPanel.add(jButton);


    javax.swing.KeyStroke keyStroke = javax.swing.KeyStroke.getKeyStroke("ctrl B");
    InputMap inputMap = jPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    inputMap.put(keyStroke,"changeColor");
    ActionMap actionMap = jPanel.getActionMap();
    actionMap.put("changeColor", myAction);

    /*
    суть
    есть inputmap  которую мы достаем из панельки и говорим,
    что хотим использовать ту самую маму которая обрабатывает все запросы на данный панельки
    вне зависимости где будет фокус

    мы достали эту мапу
    мы поместили (сочетание клавиш + обьект)
    также есть actionmap и говорим что когда вызывается такой то обьект мы вызываем такой то action
     */
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
