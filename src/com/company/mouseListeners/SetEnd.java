package com.company.mouseListeners;

import com.company.entities.End;
import com.company.view.Board;

public class SetEnd extends StartAndEndTemplate{
    public SetEnd(Board board, End end) {
        super(board, end);
        name = "End point";
    }
}
