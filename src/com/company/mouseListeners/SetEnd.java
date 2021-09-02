package com.company.mouseListeners;

import com.company.entities.End;
import com.company.view.Board;

public class SetEnd extends MouseListenerTemplate{
    public SetEnd(Board board) {
        super(board);
        toSet = new End();
        board.setEnd((End) toSet);
        name = "End point";
    }

}
