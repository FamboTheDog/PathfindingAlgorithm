package com.company.mouseListeners;

import com.company.entities.Wall;
import com.company.view.Board;

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
                super.mousePressed(e);
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
