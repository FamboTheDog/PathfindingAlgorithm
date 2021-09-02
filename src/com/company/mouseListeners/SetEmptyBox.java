package com.company.mouseListeners;

import com.company.entities.EmptyBox;
import com.company.entities.Entity;
import com.company.view.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SetEmptyBox extends MouseListenerTemplate{
    public SetEmptyBox(Board board) {
        super(board);
        toSet = new EmptyBox();
        name = "Eraser";
    }

    public void mouseDragged(MouseEvent e){
        Point coordinates = calculatePosition(e);
        board.getButtons()[coordinates.y][coordinates.x] = toSet;
        toSet.setX(coordinates.x);
        toSet.setY(coordinates.y);
        placed = false;
        board.repaint();
    }
}
