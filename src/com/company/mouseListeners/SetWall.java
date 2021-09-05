package com.company.mouseListeners;

import com.company.entities.Wall;
import com.company.view.Board;

public class SetWall extends WallTemplate {
    public SetWall(Board board, StartAndEndTemplate setStart, StartAndEndTemplate setEnd) {
        super(board,setStart,setEnd);
        toSet = new Wall();
        name = "Wall";
    }
}
