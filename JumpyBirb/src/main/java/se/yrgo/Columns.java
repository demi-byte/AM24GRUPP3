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
    public int scoreTick;

    private JumpyBirb jumpyBirb;

    private Image jungleSprite;
    private Image jungleSprite2;
    private Image underwaterSprite;
    private Image underwaterSprite2;
    private Image desertSprite;
    private Image desertSprite2;
    private Image snowSprite;
    private Image snowSprite2;
    private Image spaceSprite;
    private Image spaceSprite2;


    public Columns(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;
        scoreTick = -10;

        jungleSprite = new ImageIcon("src/images/jungleSprite.png").getImage();
        jungleSprite2 = new ImageIcon("src/images/jungleSprite2.png").getImage();
        underwaterSprite = new ImageIcon("src/images/underwaterSprite.png").getImage();
        underwaterSprite2 = new ImageIcon("src/images/underwaterSprite2.png").getImage();
        desertSprite = new ImageIcon("src/images/desertSprite.png").getImage();
        desertSprite2 = new ImageIcon("src/images/desertSprite2.png").getImage();
        snowSprite = new ImageIcon("src/images/snowSprite.png").getImage();
        snowSprite2 = new ImageIcon("src/images/snowSprite2.png").getImage();
        spaceSprite = new ImageIcon("src/images/spaceSprite.png").getImage();
        spaceSprite2 = new ImageIcon("src/images/spaceSprite2.png").getImage();

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

        for (int i = 0; i < columnsList.size(); i+=2) {
            Rectangle column1 = columnsList.get(i);
            Rectangle column2 = columnsList.get(i+1);

            if (column1.x + column1.width < 0) {
                columnsList.remove(column1);
                columnsList.remove(column2);
                addColumn(false);
            }
        }

        scoreTick++;
        if (scoreTick > 100) {
            scoreTick = 0;
            jumpyBirb.score++;
        }
    }

    public void paintColumn(Graphics g) {
        int even = 1;

        for (Rectangle column : columnsList) {
            // g.setColor(Color.cyan.darker());
            // g.fillRect(column.x, column.y, column.width, column.height);

            if (jumpyBirb.ticks < 500) {
                if (even % 2 == 0) {
                    g.drawImage(jungleSprite2, column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(jungleSprite, column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 500 && jumpyBirb.ticks < 1000) {
                if (even % 2 == 0) {
                    g.drawImage(underwaterSprite2, column.x, column.y, column.width, column.height,
                            jumpyBirb);
                } else {
                    g.drawImage(underwaterSprite, column.x, column.y, column.width, column.height,
                            jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 1000 && jumpyBirb.ticks < 1500) {
                if (even % 2 == 0) {
                    g.drawImage(desertSprite2, column.x, column.y, column.width, column.height, jumpyBirb);
                }

                else {
                    g.drawImage(desertSprite, column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 1500 && jumpyBirb.ticks < 2000) {
                if (even % 2 == 0) {
                    g.drawImage(snowSprite2, column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(snowSprite, column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            else if (jumpyBirb.ticks >= 2000) {
                if (even % 2 == 0) {
                    g.drawImage(spaceSprite2, column.x, column.y, column.width, column.height, jumpyBirb);
                } else {
                    g.drawImage(spaceSprite, column.x, column.y, column.width, column.height, jumpyBirb);
                }
            }

            even++;
        }
    }
}
