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
    private ImageIcon jungleSprite2;
    private ImageIcon underwaterSprite;
    private ImageIcon underwaterSprite2;
    private ImageIcon desertSprite;
    private ImageIcon desertSprite2;
    private ImageIcon snowSprite;
    private ImageIcon snowSprite2;
    private ImageIcon spaceSprite;
    private ImageIcon spaceSprite2;

    public Columns(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        jungleSprite = new ImageIcon("src/images/jungleSprite.png");
        jungleSprite2 = new ImageIcon("src/images/jungleSprite2.png");
        underwaterSprite = new ImageIcon("src/images/underwaterSprite.png");
        underwaterSprite2 = new ImageIcon("src/images/underwaterSprite2.png");
        desertSprite = new ImageIcon("src/images/desertSprite.png");
        desertSprite2 = new ImageIcon("src/images/desertSprite2.png");
        snowSprite = new ImageIcon("src/images/snowSprite.png");
        snowSprite2 = new ImageIcon("src/images/snowSprite2.png");
        spaceSprite = new ImageIcon("src/images/spaceSprite.png");
        spaceSprite2 = new ImageIcon("src/images/spaceSprite2.png");

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
     * 
     * @param start - checks if it's the initial setup of columns or not. New
     *              columns
     *              are added to a different location when the game movement is
     *              started.
     *              The columns added are relative (in position) to the columns
     *              added before.
     */
    public void addColumn(boolean start) {
        columnHeight = random.nextInt(250);

        if (start) {
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + columnsList.size() * 300,
                    Window.frameHeight - columnHeight, columnWidth, columnHeight));
            columnsList.add(new Rectangle(Window.frameWidth + columnWidth + (columnsList.size() - 1) * 300, 0,
                    columnWidth, Window.frameHeight - columnHeight - columnSpace));
        } else {
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x + 600,
                    Window.frameHeight - columnHeight, columnWidth, columnHeight));
            columnsList.add(new Rectangle(columnsList.get(columnsList.size() - 1).x, 0, columnWidth,
                    Window.frameHeight - columnHeight - columnSpace));
        }

    }

    public void move() {

        // collision handling
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
        int even = 1;
        for (Rectangle column : columnsList) {
            // g.setColor(Color.cyan.darker());
            // g.fillRect(column.x, column.y, column.width, column.height);

            if (jumpyBirb.ticks < 500) {
                if (even % 2 == 0) {
                    g.drawImage(jungleSprite2.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(jungleSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 500 && jumpyBirb.ticks < 1000) {
                if (even % 2 == 0) {
                    g.drawImage(underwaterSprite2.getImage(), column.x, column.y, column.width, column.height,
                            jumpyBirb);
                } else {
                    g.drawImage(underwaterSprite.getImage(), column.x, column.y, column.width, column.height,
                            jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 1000 && jumpyBirb.ticks < 1500) {
                if (even % 2 == 0) {
                    g.drawImage(desertSprite2.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                }

                else {
                    g.drawImage(desertSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 1500 && jumpyBirb.ticks < 2000) {
                if (even % 2 == 0) {
                    g.drawImage(snowSprite2.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(snowSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                }
            } 
            
            else if (jumpyBirb.ticks >= 2000) {
                if (even % 2 == 0) {
                    g.drawImage(spaceSprite2.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(spaceSprite.getImage(), column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            even++;

        }
    }
}
