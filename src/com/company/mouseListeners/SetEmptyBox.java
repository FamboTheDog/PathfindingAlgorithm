package com.company.mouseListeners;

import com.company.entities.EmptyBox;
import com.company.entities.End;
import com.company.entities.Start;
import com.company.entities.Wall;
import com.company.view.Board;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SetEmptyBox extends MouseListenerTemplate{
    public SetEmptyBox(Board board) {
        super(board);
        toSet = new EmptyBox();
        name = "Eraser";
    }

    private boolean canPlace = true;

    public void mouseDragged(MouseEvent e){
        if(canPlace){
            Point coordinates = calculatePosition(e);
            if(board.getButtons()[coordinates.y][coordinates.x].getClass() == Wall.class){
                board.getButtons()[coordinates.y][coordinates.x] = new EmptyBox();
                board.getButtons()[coordinates.y][coordinates.x].setX(coordinates.x);
                board.getButtons()[coordinates.y][coordinates.x].setY(coordinates.y);
            }
            board.repaint();
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
