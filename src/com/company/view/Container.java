package com.company.view;

import com.company.view.toolbar.Toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Container extends JPanel {

    Board board;

    public Container(){
        this.setLayout(new BorderLayout());
        board = new Board();
        this.add(board, BorderLayout.CENTER);

        Toolbar toolbar = new Toolbar(board);
        this.add(toolbar, BorderLayout.WEST);
    }

}
