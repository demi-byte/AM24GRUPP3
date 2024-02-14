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

    public Birb birb;
    public Columns columns;
    private JumpyGraphics graphics;


    /**
     * This constructor sets up a JPanel object (makeGraphics), a JFrame window and a timer
     * to make the game run forwards.
     * It also sets up the list consisting of the columns that are moving in the game,
     * and the random variable that decides the y position of the columns. .
     *
     */
    public JumpyBirb() {
        Timer timer = new Timer(20, this);
        birb = new Birb(this);
        columns = new Columns(this);
        graphics = new JumpyGraphics(this);
        gameSpeed = 6;
        ticks = 0;

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
        graphics.draw(g);
    }

    /**
     * This method is called at the end of the actionPerformed method.
     * It repaints the objects after the positions are updated.
     * The graphics are following the back end stuff.
     * */


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