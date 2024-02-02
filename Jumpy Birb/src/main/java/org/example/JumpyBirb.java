package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class JumpyBirb implements ActionListener {

    public static JumpyBirb jumpyBirb;
    public More more;
    public Random random;
    public ArrayList<Rectangle> columns;
    public Rectangle bird;
    public int ticks, yMotion;
    public final int frameHeight = 600, frameWidth = 1200;

    // konstruktorn
    public JumpyBirb() {
        JFrame jFrame = new JFrame();
        Timer timer = new Timer(20, this);

        more = new More();
        random = new Random();

        jFrame.add(more);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.setTitle("Jumpy Birb");
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        bird = new Rectangle(frameWidth / 2 - 10, frameHeight / 2 - 10, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }


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

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.cyan.darker());
        g.fillRect(column.x, column.y, column.width, column.height);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int speed = 6;
        ticks++;

        for (int i = 0; i < columns.size(); i++) {
            Rectangle column = columns.get(i);
            column.x -= speed;
        }

        if (ticks % 2 == 0 && yMotion < 15 ) {
            yMotion += 2;
        }

        for (int i = 0; i < columns.size(); i++) {
            Rectangle column = columns.get(i);

            if(column.x + column.width < 0) {
                columns.remove(column);

                if(column.y == 0) {
                    addColumn(false);
                }
            }
        }

        more.repaint();

    }

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


        for (Rectangle column: columns
             ) { paintColumn(g, column);

        }
    }

    public static void main(String[] args) {
        jumpyBirb = new JumpyBirb();
    }
}