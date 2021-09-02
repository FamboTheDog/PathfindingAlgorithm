package com.company.entities;

import java.awt.*;

public class End extends Entity{

    public End(){
        color = Color.red;
    }

    @Override
    public int compareTo(Entity o) {
        return 0;
    }
}
