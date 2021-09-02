package com.company.view;

import com.company.entities.EmptyBox;
import com.company.entities.End;
import com.company.entities.Entity;
import com.company.entities.Start;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Board extends JPanel {

    @Getter private Entity[][] buttons;
    @Getter private final int rows    = 30;
    @Getter private final int columns = 30;
    @Getter @Setter private Start start;
    @Getter @Setter private End end;

    @Setter private Entity shortestPath;

    Board(){
        buttons = new Entity[rows][columns];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new EmptyBox(j,i);
            }
        }
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

        shortestPath(shortestPath, gd, tileWidth, tileHeight);
    }

    private void shortestPath(Entity node, Graphics2D gd, float tileWidth, float tileHeight){
        if(node == null) return;

        gd.setColor(Color.blue);
        gd.fill(new Rectangle2D.Double(node.getX() * tileWidth, node.getY() * tileHeight, tileWidth, tileHeight));
        gd.setColor(Color.black);
        gd.draw(new Rectangle2D.Double(node.getX() * tileWidth, node.getY() * tileHeight, tileWidth, tileHeight));

        shortestPath(node.getParent(), gd, tileWidth, tileHeight);
    }

}
