package com.company.algorithms;

import com.company.entities.*;
import com.company.view.Board;

import java.awt.*;
import java.util.*;

public class AStar {

    private final Board board;

    public AStar(Board board){
        this.board = board;
    }

    public void aStar(Start start, End end){
        ArrayList<Entity> open = new ArrayList<>();
        ArrayList<Entity> closed = new ArrayList<>();
        start.setG(0);
        start.setF(0);

        open.add(start);

        while(!open.isEmpty()){
            Entity currentEntity = calculateLowestF(open);
            open.remove(currentEntity);
            closed.add(currentEntity);

            if(currentEntity == end) {
                board.startTimer(closed, currentEntity);
                return;
            }

            int[][] adjacentTiles = {
                    {1, 0}, {0, 1}, {-1,0}, {0,-1},  // normal distance
                    {-1,-1}, {1, 1}, {-1,1}, {1,-1}  // diagonal distance
            };
            Point currentLocation = new Point(currentEntity.getX(), currentEntity.getY());
            int counter = 0;
            for (int[] cur : adjacentTiles) {
                counter++;
                int x = currentLocation.x + cur[0];
                int y = currentLocation.y + cur[1];
                if(isNotInBounds(x, y)) continue;

                Entity n = board.getButtons()[y][x];

                if(closed.contains(n)) continue;
                if(n.getClass() == Wall.class) continue;

                double distance = counter < 4 ? n.getD() : n.getD2(); // get either normal distance or diagonal distance
                double totalWeight = (currentEntity.getG() + distance);

                if(totalWeight < n.getG()){
                    n.setParent(currentEntity);
                    n.setG(totalWeight);
                    n.setF(n.getG() + n.calculateHeuristics(end));

                    if(!open.contains(n)) {
                        open.add(n);
                    }
                }
            }
        }
        System.out.println("algorithm failed");
        board.startTimer(closed, null);
    }

    private Entity calculateLowestF(ArrayList<Entity> open){
        Entity lowest = null;
        double fScore = Double.MAX_VALUE;

        for (Entity entity : open) {
            if (entity.getF() < fScore) {
                fScore = entity.getF();
                lowest = entity;
            }
        }
        return lowest;
    }

    private boolean isNotInBounds(int x, int y){
        return  x < 0 ||
                x > board.getButtons()[0].length - 1 ||
                y < 0 ||
                y > board.getButtons().length - 1;
    }
}
