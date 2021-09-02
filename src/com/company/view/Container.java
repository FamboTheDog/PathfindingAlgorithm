package com.company.view;

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

        /*
        JMenuBar optionsBar = new JMenuBar();
        optionsBar.setVisible(true);

        JMenu edit = new JMenu("Edit");
        JMenuItem setSize = new JMenuItem("Set size");
        edit.add(setSize);

        JMenu add = new JMenu("Add components");

        JMenuItem startPoint = new JMenuItem("Add start point");
        MouseListenerTemplate setStartPoint = new SetStart(board);
        startPoint.addActionListener(e->{
            removeAllMouseListeners();
            board.addMouseListener(setStartPoint);
        });
        add.add(startPoint);

        JMenuItem endPoint = new JMenuItem("Add end Point");
        MouseListenerTemplate setEndPoint = new SetEnd(board);
        endPoint.addActionListener(e->{
            removeAllMouseListeners();
            board.addMouseListener(setEndPoint);
        });
        add.add(endPoint);


        Astar astar = new Astar(board);
        JButton start = new JButton("s");
        start.addActionListener(e->{
            Entity entity = astar.aStar(board.getStart(), board.getEnd());
            // astar.getPath(entity);
            board.setShortestPath(entity);
            board.repaint();
        });

        optionsBar.add(edit);
        optionsBar.add(add);
        optionsBar.add(start);

        this.add(optionsBar, BorderLayout.NORTH);
         */
    }

    private void removeAllMouseListeners(){
        for (MouseListener listener:
             board.getMouseListeners()) {
            board.removeMouseListener(listener);
        }
    }

}
