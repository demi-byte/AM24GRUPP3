package se.yrgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class JumpyBirb extends JPanel implements ActionListener, MouseListener, KeyListener {

    public boolean gameOver, started;


    public int gameSpeed, ticks, score;

    public final Birb birb;
    public Columns columns;
    private final JumpyGraphics graphics;
    

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
        gameSpeed = 10;
        ticks = 0;
        score = 0;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);

        timer.start();
    }

    /**
     * This method is called everytime the timer hits 20 ms as we set it up to.
     * In the method the columns are moving to the left at a fixed rate.
     * When the columns are out of the visible area (to the left) they are removed
     * from the column list.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (started) {
            ticks++;
            columns.move();
            birb.update();
        }


        if (gameOver) {
            restart();
        }


        this.repaint();
    }

    public void restart() {
        gameOver = false;
        started = false;
        ticks = 0;
        score = 0;
        columns = new Columns(this);
        birb.birbRect.x = Window.frameWidth / 2 - 25;
        birb.birbRect.y = Window.frameHeight / 2 - 25;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics.draw(g);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            started = true;
            birb.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}