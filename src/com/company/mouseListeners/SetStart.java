package com.company.mouseListeners;

import com.company.entities.Start;
import com.company.view.Board;

public class SetStart extends StartAndEndTemplate {
    public SetStart(Board board, Start start){
        super(board,start);
        name = "Start point";
    }
}
