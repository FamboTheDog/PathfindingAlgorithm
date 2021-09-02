package com.company.entities;

import java.awt.*;

public class Wall extends Entity{

    public Wall(){
        color = Color.black;
        d = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Entity o) {
        return 0;
    }

}
