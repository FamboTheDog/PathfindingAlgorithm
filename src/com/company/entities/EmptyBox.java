package com.company.entities;

import java.awt.*;

public class EmptyBox extends Entity{

    public EmptyBox(){
        color = Color.white;
    }

    public EmptyBox(int x, int y){
        color = Color.white;
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Entity o) {
        return 0;
    }
}
