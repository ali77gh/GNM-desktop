package com.gnm.desktop.ui;

import javax.swing.*;

public class MainWindow {
    private JPanel root;

    public MainWindow(){

    }

    public static void Show(){
        JFrame frame = new JFrame();
        frame.setContentPane(new MainWindow().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//make full screen
        frame.pack();
        frame.setVisible(true);
    }
}
