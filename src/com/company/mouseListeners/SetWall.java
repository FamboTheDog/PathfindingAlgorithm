package com.company.mouseListeners;

import com.company.entities.Wall;
import com.company.view.Board;

import java.awt.event.MouseEvent;

public class SetWall extends MouseListenerTemplate{
    public SetWall(Board board) {
        super(board);
        toSet = new Wall();
        name = "Wall";
    }

    public void mouseDragged(MouseEvent e){
        mousePressedAction(e);
        placed = false;
    }
}
