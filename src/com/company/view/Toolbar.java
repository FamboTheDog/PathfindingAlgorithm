package com.company.view;

import com.company.algorithms.AStar;
import com.company.entities.Entity;
import com.company.mouseListeners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Toolbar extends JPanel implements ActionListener {

    private Board board;
    private JComboBox<MouseListenerTemplate> entitySelector;

    Toolbar(Board board){
        this.board = board;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 1;
        gbc.gridy = 1;

        AStar aStar = new AStar(board);

        JLabel heading = new JLabel("<html><h1>A* algorithm</h1></html>", JLabel.CENTER);
        this.add(heading, gbc);
        gbc.gridy++;

        entitySelector = new JComboBox<>();
        SetStart setStart = new SetStart(board);
        entitySelector.addItem(setStart);
        SetEnd setEnd = new SetEnd(board);
        entitySelector.addItem(setEnd);
        SetWall setWall = new SetWall(board);
        entitySelector.addItem(setWall);
        SetEmptyBox setEmptyBox = new SetEmptyBox(board);
        entitySelector.addItem(setEmptyBox);
        entitySelector.addActionListener(this);
        entitySelector.setPreferredSize(new Dimension(100,50));
        this.add(entitySelector, gbc);
        gbc.gridy++;

        board.addMouseListener(setStart);

        JButton start = new JButton("Start algorithm");
        start.addActionListener(e->{
            Entity entity = aStar.aStar(board.getStart(), board.getEnd());
            board.setShortestPath(entity);
            board.repaint();
        });
        this.add(start,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MouseListenerTemplate source = (MouseListenerTemplate) entitySelector.getSelectedItem();
        for(MouseListener listener : board.getMouseListeners()){
            board.removeMouseListener(listener);
        }
        for(MouseMotionListener listener : board.getMouseMotionListeners()){
            board.removeMouseMotionListener(listener);
        }
        board.addMouseListener(source);
        board.addMouseMotionListener(source);
    }
}
