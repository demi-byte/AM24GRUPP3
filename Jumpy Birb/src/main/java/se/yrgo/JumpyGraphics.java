package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class JumpyGraphics {

    public ImageIcon backgroundImage;
    public JLabel backgroundLabel;

    private JumpyBirb jumpyBirb;

    public JumpyGraphics(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        backgroundImage = new ImageIcon("");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(100, 100, 101, 79);
    }

    public void draw(Graphics g) {

        //sky
        if (jumpyBirb.ticks < 100)
            g.setColor(Color.pink);
        else if (jumpyBirb.ticks >= 100 && jumpyBirb.ticks < 200)
            g.setColor(Color.blue);
        else if (jumpyBirb.ticks >= 200 && jumpyBirb.ticks < 300)
            g.setColor(Color.magenta);
        else if (jumpyBirb.ticks >= 300 && jumpyBirb.ticks < 400)
            g.setColor(Color.green);
        else
            g.setColor(Color.white);

        g.fillRect(0, 0, Window.frameWidth, Window.frameHeight);

        //ground
        g.setColor(Color.gray);
        g.fillRect(0, Window.frameHeight - 120, Window.frameWidth, 120);

        //grass
        g.setColor(Color.darkGray);
        g.fillRect(0, Window.frameHeight - 120, Window.frameWidth, 20);

        //birb
        jumpyBirb.birb.paintBirb(g);

        //columns
        jumpyBirb.columns.paintColumn(g);


        //text
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 1, 100));

        if (!jumpyBirb.started) {
            g.drawString("Press space to start!", 75, Window.frameHeight / 2 -50);
        }

        if (jumpyBirb.gameOver) {
            g.drawString("Game Over, you suck!", 75, Window.frameHeight / 2 -50);
        }
    }

}
