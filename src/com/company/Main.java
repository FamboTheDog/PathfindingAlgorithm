package com.company;


import com.company.view.Container;

import javax.swing.*;
import java.awt.*;

public class Main {
    /*
    * TODO:
    *  - checkbox to allow or disallow diagonals
    *
    * */
    public static void main(String[] args) {
        final String title = "Pathfinder";
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setSize(new Dimension(640,480));
        frame.setVisible(true);

        Container container = new Container();
        frame.add(container);

        frame.revalidate();
        frame.repaint();
    }
}
