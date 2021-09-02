package com.company.algorithms;

import com.company.entities.End;
import com.company.entities.Entity;
import com.company.entities.Start;
import com.company.entities.Wall;
import com.company.view.Board;

import java.awt.*;
import java.util.*;

public class AStar {

    private Board board;

    public AStar(Board board){
        this.board = board;
    }

    public Entity aStar(Start start, End end){
        ArrayList<Entity> open = new ArrayList<>();
        open.add(start);
        start.setG(0);
        start.setF(start.calculateHeuristics(end));

        while(!open.isEmpty()){
            Entity currentEntity = calculateLowestF(open);
            if(currentEntity == end) return currentEntity;
            open.remove(currentEntity);

            int[][] adjacentTiles = {{1, 0}, {0, 1}, {-1,0}, {0,-1}, {-1,-1}, {1, 1}, {-1,1}, {1,-1}};
            Point currentLocation = new Point(currentEntity.getX(), currentEntity.getY());
            for (int[] cur : adjacentTiles) {
                if(currentLocation.x + cur[0] < 0 ||
                   currentLocation.x + cur[0] > board.getButtons()[0].length - 1 ||
                   currentLocation.y + cur[1] < 0 ||
                   currentLocation.y + cur[1] > board.getButtons().length - 1) continue;

                Entity n = board.getButtons()[currentLocation.y + cur[1]][ currentLocation.x + cur[0]];
                System.out.println(n);
                if(n.getClass() == Wall.class) continue;
                int totalWeight = currentEntity.getG() + n.getD();

                if(totalWeight < n.getG()){
                    n.setParent(currentEntity);
                    n.setG(totalWeight);
                    n.setF(n.getG() + n.calculateHeuristics(end));

                    if(!open.contains(n)) open.add(n);
                }
            }
        }
        System.out.println("algorithm failed");
        return null;
    }

    private Entity calculateLowestF(ArrayList<Entity> open){
        Entity lowest = null;
        Integer fScore = Integer.MAX_VALUE;

        for (Entity entity : open) {
            if (entity.getF() < fScore) {
                fScore = entity.getF();
                lowest = entity;
            }
        }
        return lowest;
    }

}
