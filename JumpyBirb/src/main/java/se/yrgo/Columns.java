package se.yrgo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Columns {
    public ArrayList<Rectangle> columnsList;
    public Random random;
    public int columnSpace;
    public int columnWidth;
    public int columnHeight;

    private JumpyBirb jumpyBirb;

    private ImageIcon jungleSprite;
    private ImageIcon underwaterSprite;
    private ImageIcon desertSprite;


    public Columns(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        jungleSprite = new ImageIcon("src/images/jungleSprite.png");
        underwaterSprite = new ImageIcon("src/images/underwaterSprite.png");
        desertSprite = new ImageIcon("src/images/desertSprite.png");

        columnsList = new ArrayList<>();
        random = new Random();
        columnSpace = 300;
        columnWidth = 100;

        for (int i = 0; i < 3; i++) {
            addColumn(true);
        }

    }

    /**
     * This method adds a column (obstruction)
     * @param start - checks if it's the initial setup of columns or not. New columns
     *              are added to a different location when the game movement is started.
     *              The columns added are relative (in position) to the columns added before.
     * */
    public void addColumn(boolean start) {
        columnHeight = random.nextInt(250);

        if (start) {
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + columnsList.size() * 300, Window.frameHeight - columnHeight, columnWidth, columnHeight));
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + (columnsList.size() - 1) * 300, 0, columnWidth, Window.frameHeight - columnHeight - columnSpace));
        }
        else {
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x + 600, Window.frameHeight - columnHeight, columnWidth, columnHeight));
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x, 0, columnWidth, Window.frameHeight - columnHeight - columnSpace));
        }

    }

    public void move() {

        //collision handling
        for (Rectangle column : columnsList) {
            if (column.intersects(jumpyBirb.birb.birbRect)) {
                jumpyBirb.gameOver = true;
                jumpyBirb.birb.birbRect.x = column.x - jumpyBirb.birb.birbRect.width;
            }
        }

        for (Rectangle column : columnsList) {
            column.x -= jumpyBirb.gameSpeed;
        }

        for (int i = 0; i < columnsList.size(); i++) {
            Rectangle column = columnsList.get(i);

            if (column.x + column.width < 0) {
                columnsList.remove(column);
                addColumn(false);
            }
        }
    }

    public void paintColumn(Graphics g) {
        for (Rectangle column: columnsList) {
            //g.setColor(Color.cyan.darker());
            //g.fillRect(column.x, column.y, column.width, column.height);


            if (jumpyBirb.ticks < 250) {
                g.drawImage(jungleSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
            }

            else if (jumpyBirb.ticks >= 250 && jumpyBirb.ticks < 500) {
                g.drawImage(underwaterSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
            }

            else if (jumpyBirb.ticks >= 500 && jumpyBirb.ticks < 750) {
                g.drawImage(desertSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
            }


        }
    }
}
