package com.company.view;

import com.company.entities.EmptyBox;
import com.company.entities.End;
import com.company.entities.Entity;
import com.company.entities.Start;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    @Getter private Entity[][] buttons;
    @Getter @Setter private int rows    = 30;
    @Getter @Setter private int columns = 30;
    @Getter @Setter private Start start;
    @Getter @Setter private End     end;

    @Getter @Setter private ArrayList<Entity> addToCheckedTiles = new ArrayList<>();
    @Getter @Setter private ArrayList<Entity> checkedTiles      = new ArrayList<>();

    private Entity shortestPathCopy;
    @Setter private Entity shortestPath;

    int counter = 0;
    Timer showCheckedTiles = new Timer(25,this);

    Board(){
        start = new Start();
        end   = new End();
        buttons = new Entity[rows][columns];
        setInitialBoard();
    }

    public void emptyBoard(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new EmptyBox(j,i);
            }
        }
    }

    public void setInitialBoard() {
        emptyBoard();
        start = new Start();
        end   = new End();
        buttons[0][0] = start;
        start.setX(0);
        start.setY(0);
        buttons[buttons.length - 1][buttons[0].length - 1] = end;
        end.setX(buttons.length - 1);
        end.setY(buttons[0].length - 1);
        System.out.println(start);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        float tileWidth  = (float) getWidth()  / columns;
        float tileHeight = (float) getHeight() / rows;

        float x;
        float y = -tileHeight;

        for (Entity[] row: buttons) {
            y += tileHeight;
            x = 0;
            for (Entity button: row) {
                Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
                gd.setColor(button.getColor());
                gd.fill(rect);
                gd.setColor(Color.black);
                gd.draw(rect); // border

                x += tileWidth;
            }
        }


        checkedTiles.forEach(entity -> drawFilledSquare(gd, Color.green, entity, tileWidth, tileHeight));

        // shortestPath must have a higher priority than checked tiles to show up!
        shortestPath(shortestPath, gd, tileWidth, tileHeight);

        // start and end are also included in shortestPath, so we need to paint them again
        if(start != null) drawFilledSquare(gd, start.getColor(), start, tileWidth, tileHeight);

        if(end != null) drawFilledSquare(gd, end.getColor(), end, tileWidth, tileHeight);
    }

    public void drawFilledSquare(Graphics2D gd, Color color, Entity obj, float tileWidth, float tileHeight){
        gd.setColor(color);
        gd.fill(new Rectangle2D.Double(obj.getX() * tileWidth, obj.getY() * tileHeight, tileWidth, tileHeight));
        gd.setColor(Color.black);
        gd.draw(new Rectangle2D.Double(obj.getX() * tileWidth, obj.getY() * tileHeight, tileWidth, tileHeight));
    }

    private void shortestPath(Entity node, Graphics2D gd, float tileWidth, float tileHeight){
        if(node == null) return;

        drawFilledSquare(gd, Color.cyan, node, tileWidth, tileHeight);

        shortestPath(node.getParent(), gd, tileWidth, tileHeight);
    }

    public void startTimer(ArrayList<Entity> checkedTiles, Entity shortestPath){
        this.addToCheckedTiles = checkedTiles;
        this.shortestPathCopy = shortestPath;
        counter = 0;

        showCheckedTiles.setInitialDelay(0);
        showCheckedTiles.setRepeats(true);
        showCheckedTiles.start();
    }

    public void restart(){
        showCheckedTiles.stop();
        shortestPathCopy = null;
        shortestPath = null;
        checkedTiles.clear();
        addToCheckedTiles.clear();
        setInitialBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(addToCheckedTiles.size() > checkedTiles.size()){
            checkedTiles.add(addToCheckedTiles.get(counter));
            counter++;
        }else{
            shortestPath = shortestPathCopy;
            showCheckedTiles.stop();
        }
        repaint();
    }


}
