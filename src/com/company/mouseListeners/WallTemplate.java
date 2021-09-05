package com.company.mouseListeners;

import com.company.entities.EmptyBox;
import com.company.entities.Wall;
import com.company.view.Board;

import java.awt.*;
import java.awt.event.MouseEvent;

public class WallTemplate extends MouseListenerTemplate{
    public WallTemplate(Board board, StartAndEndTemplate setStart, StartAndEndTemplate setEnd) {
        super(board);
        this.setStart = setStart;
        this.setEnd = setEnd;
    }
    boolean canPlace = true;

    StartAndEndTemplate setStart;
    StartAndEndTemplate setEnd;

    @Override
    public void mousePressed(MouseEvent e) {
        if(canPlace){
            if(!setStart.isActive && !setEnd.isActive) super.mousePressed(e);
        }
    }

    public void mouseDragged(MouseEvent e){
        if(canPlace){
            if(!setStart.isActive && !setEnd.isActive){
                Point position = calculatePosition(e);
                if(board.getButtons()[position.y][position.x].getClass() == EmptyBox.class){
                    board.getButtons()[position.y][position.x] = toSet;
                    toSet.setX(position.x);
                    toSet.setY(position.y);
                    board.repaint();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        canPlace = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseEntered(e);
        canPlace = false;
    }
}
