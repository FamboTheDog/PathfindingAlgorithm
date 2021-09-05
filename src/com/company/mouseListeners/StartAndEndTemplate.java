package com.company.mouseListeners;

import com.company.entities.EmptyBox;
import com.company.entities.Entity;
import com.company.view.Board;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartAndEndTemplate extends MouseListenerTemplate{
    @Setter protected Entity toSet;
    public StartAndEndTemplate(Board board, Entity control) {
        super(board);
        toSet = control;
    }

    boolean changePosition = false;
    Point lastPosition;

    @Getter boolean isActive = false;

    @Override
    public void mousePressed(MouseEvent e) {
        Point position = calculatePosition(e);
        System.out.println(toSet);
        if(board.getButtons()[position.y][position.x] == toSet){
            isActive = true;
            changePosition = true;
            lastPosition = position;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(changePosition){
            Point position = calculatePosition(e);
            board.getButtons()[position.y][position.x] = toSet;
            toSet.setX(position.x);
            toSet.setY(position.y);
            board.getButtons()[lastPosition.y][lastPosition.x] = new EmptyBox(lastPosition.x, lastPosition.y);
            board.repaint();
            changePosition = false;
        }
        isActive = false;
    }
}
