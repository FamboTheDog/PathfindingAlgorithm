package com.company.mouseListeners;

import com.company.entities.EmptyBox;
import com.company.entities.Entity;
import com.company.view.Board;
import lombok.Getter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract public class MouseListenerTemplate extends MouseAdapter implements MouseMotionListener {

    protected Board board;

    protected Entity toSet;
    @Getter protected Color playerColor;
    protected boolean placed = false;
    protected String name = "";


    public MouseListenerTemplate(Board board){
        this.board = board;
    }

    public Point calculatePosition(MouseEvent e){
        float tileWidth  = (float) board.getWidth() / board.getColumns();
        float tileHeight = (float) board.getHeight() / board.getRows();

        return new Point((int) (e.getX() / tileWidth), (int) (e.getY() / tileHeight));
    }

    public void mousePressed(MouseEvent e){
        mousePressedAction(e);
    }

    protected void mousePressedAction(MouseEvent e){
        if(!placed) {
            placed = true;
            Point coordinates = calculatePosition(e);
            board.getButtons()[coordinates.y][coordinates.x] = toSet;
            toSet.setX(coordinates.x);
            toSet.setY(coordinates.y);
        }
        board.repaint();
    }

    public void mouseDragged(MouseEvent e){}

    @Override
    public String toString() {
        return name;
    }
}

