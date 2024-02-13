package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class JumpyBirb implements ActionListener, MouseListener {

    public static JumpyBirb jumpyBirb;
    public MakeGraphics makeGraphics;
    public Random random;
    public ArrayList<Rectangle> columns;
    public int ticks, yMovement, score;

    public boolean gameOver, started;
    public Rectangle birb;
    public final int frameHeight = 600, frameWidth = 1200;

    /**
     * This constructor sets up a JPanel object (makeGraphics), a JFrame window and a timer
     * to make the game run forwards.
     * It also sets up the list consisting of the columns that are moving in the game,
     * and the random variable that decides the y position of the columns. .
     *
     */
    public JumpyBirb() {
        JFrame jFrame = new JFrame();
        Timer timer = new Timer(20, this);

        makeGraphics = new MakeGraphics();
        random = new Random();

        jFrame.add(makeGraphics);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.addMouseListener(this);
        jFrame.setTitle("Jumpy Birb");
        jFrame.setResizable(false);
        jFrame.setVisible(true);


        birb = new Rectangle(frameWidth / 2 - 25, frameHeight / 2 - 25, 50,  50);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }


    /**
     * This method adds a column (obstruction)
     * @param start - checks if it's the initial setup of columns or not. New columns
     *              are added to a different location when the game movement is started.
     *              The columns added are relative (in position) to the columns added before.
     * */
    public void addColumn(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);

        if (start) {
            columns.add(new Rectangle(frameWidth + width + columns.size() * 300, frameHeight - height - 120, width, height));
            columns.add(new Rectangle(frameWidth + width + (columns.size() - 1) * 300, 0, width, frameHeight - height - space));
        }
        else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, frameHeight - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, frameHeight - height - space));
        }

    }

    /**
     * This method paints a column.
     * */
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.cyan.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    /**
     * This method makes the birb jump
     */
    public void jump() {
        if (gameOver) {

            birb = new Rectangle(frameWidth / 2 - 25, frameHeight / 2 - 25, 50,  50);
            columns.clear();
            yMovement = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        if (!started) {
            started = true;

        } else if (!gameOver) {
            if (yMovement > 0) {
                yMovement = 0;
            }
            yMovement -= 10;
        }
    }

    /**
     * This method is called everytime the timer hits 20 ms as we set it up to.
     * In the method the columns are moving to the left at a fixed rate.
     * When the columns are out of the visible area (to the left) they are removed
     * from the column list.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {

        int speed = 6;
        ticks++;

        if (started) {
            for (Rectangle column : columns) {
                column.x -= speed;
            }


            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (ticks % 2 == 0 && yMovement < 15) {
                    yMovement += 2;
                }
                if (column.x + column.width < 0) {
                    columns.remove(column);
                    addColumn(false);
                }
            }


            birb.y += yMovement;

            for (Rectangle column : columns
            ) {

                if (column.intersects(birb)) {
                    gameOver = true;

                    birb.x = column.x - birb.width;
                }
            }

            if (birb.y > frameHeight - 120 || birb.y < 0) {
                gameOver = true;
            }
                if (birb.y + yMovement >= frameHeight - 120) {
                    birb.y = frameHeight - 120 - birb.height;
                 }
        }
        makeGraphics.repaint();
    }

    /**
     * This method is called at the end of the actionPerformed method.
     * It repaints the objects after the positions are updated.
     * The graphics are following the back end stuff.
     * */
    public void repaint(Graphics g) {

        //sky
        g.setColor(Color.pink);
        g.fillRect(0, 0, frameWidth, frameHeight);

        //ground
        g.setColor(Color.GRAY);
        g.fillRect(0, frameHeight - 120, frameWidth, 120);

        //grass
        g.setColor(Color.darkGray);
        g.fillRect(0, frameHeight - 120, frameWidth, 20);

        //birb
        g.setColor(Color.red);
        g.fillRect(birb.x, birb.y, birb.width, birb.height);

        for (Rectangle column: columns
             ) { paintColumn(g, column);

        }
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 1, 100));

        if (!started) {
            g.drawString("Press space to start!", 75, frameHeight / 2 -50);
        }

        if (gameOver) {
            g.drawString("Game Over, you suck!", 75, frameHeight / 2 -50);
        }
    }

    public static void main(String[] args) {
        jumpyBirb = new JumpyBirb();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}