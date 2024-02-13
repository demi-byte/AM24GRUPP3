package se.yrgo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Columns {
    public ArrayList<Rectangle> columnsList;
    public Random random;
    public int columnSpace;
    public int columnWidth;
    public int columnHeight;

    public Columns() {
        columnsList = new ArrayList<>();
        random = new Random();
        columnSpace = 300;
        columnWidth = 100;
        columnHeight = 50 + random.nextInt(300);

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

        if (start) {
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + columnsList.size() * 300, Window.frameHeight - columnHeight - 120, columnWidth, columnHeight));
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + (columnsList.size() - 1) * 300, 0, columnWidth, Window.frameHeight - columnHeight - columnSpace));
        }
        else {
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x + 600, Window.frameHeight - columnHeight - 120, columnWidth, columnHeight));
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x, 0, columnWidth, Window.frameHeight - columnHeight - columnSpace));
        }

    }

    public void move() {

        for (Rectangle column : columnsList) {
            column.x -= JumpyBirb.gameSpeed;
        }

        for (int i = 0; i < columnsList.size(); i++) {
            Rectangle column = columnsList.get(i);

            if (column.x + column.width < 0) {
                columnsList.remove(column);
                addColumn(false);
            }
        }
    }
}
