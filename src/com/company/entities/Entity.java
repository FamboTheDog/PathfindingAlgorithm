package com.company.entities;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

abstract public class Entity implements Comparable<Entity>{


    @Getter @Setter protected Integer d = 1; // distance
    @Getter @Setter protected double d2 = Math.sqrt(2); // diagonal distance
    @Getter @Setter protected double g = Double.MAX_VALUE;
    @Getter @Setter protected double f = Double.MAX_VALUE;

    @Getter @Setter protected Entity parent;

    @Getter @Setter protected ArrayList<Entity> neighbours;

    @Getter protected Color color;

    @Getter @Setter protected int x;
    @Getter @Setter protected int y;

    public int calculateHeuristics(End end){
        int x = Math.abs(this.x - end.x);
        int y = Math.abs(this.y - end.y);
        return (int) (d * (x + y) + (d2 - 2 * d) * Math.min(x,y));
    }

}
