package se.yrgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class JumpyBirb extends JPanel implements ActionListener, MouseListener {

    public boolean gameOver, started;

    public static int gameSpeed, ticks;

    private Birb birb;
    private Columns columns;


    /**
     * This constructor sets up a JPanel object (makeGraphics), a JFrame window and a timer
     * to make the game run forwards.
     * It also sets up the list consisting of the columns that are moving in the game,
     * and the random variable that decides the y position of the columns. .
     *
     */
    public JumpyBirb() {
        Timer timer = new Timer(20, this);
        birb = new Birb();
        columns = new Columns();
        gameSpeed = 6;
        ticks = 0;


        this.add(birb.birbLabel);

        timer.start();
        started = true;
    }


    /**
     * This method is called everytime the timer hits 20 ms as we set it up to.
     * In the method the columns are moving to the left at a fixed rate.
     * When the columns are out of the visible area (to the left) they are removed
     * from the column list.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        if (started) {
            columns.move();
            birb.fall();

            for (Rectangle column : columns.columnsList) {
                if (column.intersects(birb.birbRect)) {
                    gameOver = true;
                    birb.birbRect.x = column.x - birb.birbRect.width;
                }
            }

            if (birb.birbRect.y > Window.frameHeight - 120 || birb.birbRect.y < 0) {
                gameOver = true;
            }

            if (birb.birbRect.y + birb.yMovement >= Window.frameHeight - 120) {
                    birb.birbRect.y = Window.frameHeight - 120 - birb.birbRect.height;
            }
        }

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * This method is called at the end of the actionPerformed method.
     * It repaints the objects after the positions are updated.
     * The graphics are following the back end stuff.
     * */
    public void draw(Graphics g) {

        //sky
        g.setColor(Color.pink);
        g.fillRect(0, 0, Window.frameWidth, Window.frameHeight);

        //ground
        g.setColor(Color.gray);
        g.fillRect(0, Window.frameHeight - 120, Window.frameWidth, 120);

        //grass
        g.setColor(Color.darkGray);
        g.fillRect(0, Window.frameHeight - 120, Window.frameWidth, 20);

        //birb
        birb.paintBirb(g);

        //columns
        columns.paintColumn(g);


        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 1, 100));

        if (!started) {
            g.drawString("Press space to start!", 75, Window.frameHeight / 2 -50);
        }

        if (gameOver) {
            g.drawString("Game Over, you suck!", 75, Window.frameHeight / 2 -50);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        birb.jump();
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