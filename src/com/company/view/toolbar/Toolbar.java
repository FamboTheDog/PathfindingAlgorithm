package com.company.view.toolbar;

import com.company.algorithms.AStar;
import com.company.entities.Entity;
import com.company.mouseListeners.*;
import com.company.view.Board;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Toolbar extends JPanel implements ActionListener {

    private Board board;
    private JComboBox<MouseListenerTemplate> entitySelector;

    private MouseListenerTemplate lastListener;

    public Toolbar(Board board){
        this.board = board;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 1;
        gbc.gridy = 1;

        AStar aStar = new AStar(board);

        JLabel heading = new JLabel("<html><h1>A* Algorithm</h1></html>", JLabel.CENTER);
        this.add(heading, gbc);
        gbc.gridy++;

        entitySelector = new JComboBox<>();

        StartAndEndTemplate setStart = new SetStart(board, board.getStart());
        StartAndEndTemplate setEnd = new SetEnd(board, board.getEnd());

        System.out.println(setEnd.isActive());

        SetWall setWall = new SetWall(board, setStart, setEnd);
        entitySelector.addItem(setWall);
        SetEmptyBox setEmptyBox = new SetEmptyBox(board);
        entitySelector.addItem(setEmptyBox);
        entitySelector.addActionListener(this);
        entitySelector.setPreferredSize(new Dimension(100,50));
        this.add(entitySelector, gbc);
        gbc.gridy++;

        board.addMouseListener(setStart);
        board.addMouseMotionListener(setStart);
        board.addMouseListener(setEnd);
        board.addMouseMotionListener(setEnd);

        board.addMouseListener(setWall);
        board.addMouseMotionListener(setWall);
        lastListener = setWall;

        JLabel boardSizeText = new JLabel("Board size: ", JLabel.CENTER);
        this.add(boardSizeText, gbc);
        gbc.gridy++;

        // TODO implement this
//        JSlider boardSize = new JSlider(30,50,40);
//        boardSize.setValue(30);
//        boardSize.addChangeListener(this);
//        boardSize.setMajorTickSpacing(10);
//        boardSize.setPaintTicks(true);
//        boardSize.setPaintLabels(true);
//        this.add(boardSize,gbc);
//        gbc.gridy++;



        JButton start = new JButton("Start algorithm");
        JButton clear = new JButton("Clear algorithm");
        ButtonContainer containerButton = new ButtonContainer(start,clear);
        containerButton.showStart();
        start.addActionListener(e->{
            aStar.aStar(board.getStart(), board.getEnd());

            containerButton.showClear();
            this.revalidate();
            this.repaint();
        });
        clear.addActionListener(e->{
            board.restart();
            board.repaint();

            containerButton.showStart();
            setStart.setToSet(board.getStart());
            setEnd.setToSet(board.getEnd());
            this.revalidate();
            this.repaint();
        });

        this.add(containerButton,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MouseListenerTemplate source = (MouseListenerTemplate) entitySelector.getSelectedItem();

        board.removeMouseListener(lastListener);

        board.removeMouseMotionListener(lastListener);

        board.addMouseListener(source);
        board.addMouseMotionListener(source);

        lastListener = source;
    }
//
//    @Override
//    public void stateChanged(ChangeEvent e) {
//        JSlider source = (JSlider) e.getSource();
//        board.setRows(source.getValue());
//        board.setColumns(source.getValue());
//        board.changeGrid();
//        board.repaint();
//    }
}

