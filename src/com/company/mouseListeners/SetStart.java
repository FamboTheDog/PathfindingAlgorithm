package com.company.mouseListeners;

import com.company.entities.Start;
import com.company.view.Board;

public class SetStart extends MouseListenerTemplate {
    public SetStart(Board board){
        super(board);
        toSet = new Start();
        board.setStart((Start) toSet);
        name = "Start point";
    }
}
