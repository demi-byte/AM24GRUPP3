package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class JumpyGraphics {

    public ImageIcon backgroundImage1;
    public ImageIcon backgroundImage2;
    public ImageIcon backgroundImage3;
    public ImageIcon backgroundImage4;
    public ImageIcon backgroundImage5;
    public JLabel backgroundLabel;

    private JumpyBirb jumpyBirb;

    public JumpyGraphics(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        backgroundImage1 = new ImageIcon("src/images/jungle.jpg");
        backgroundImage2 = new ImageIcon("src/images/water.jpeg");
        backgroundImage3 = new ImageIcon("src/images/desert.jpeg");
        backgroundImage4 = new ImageIcon("src/images/snow.jpeg");
        backgroundImage5 = new ImageIcon("src/images/space.jpeg");

        //backgroundLabel = new JLabel(backgroundImage);
       // backgroundLabel.setBounds(100, 100, 101, 79);

    }

    /**
     * This method is called at the end of the actionPerformed method.
     * It repaints the objects after the positions are updated.
     * The graphics are following the back end stuff.
     * */
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
        g.fillRect(0, Window.frameHeight - JumpyBirb.groundHeight, Window.frameWidth, JumpyBirb.groundHeight);

        //grass
        g.setColor(Color.darkGray);
        g.fillRect(0, Window.frameHeight - JumpyBirb.groundHeight, Window.frameWidth, 20);


        if (jumpyBirb.ticks < 500) {
            g.drawImage(backgroundImage1.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);
        }

        else if (jumpyBirb.ticks >= 500 && jumpyBirb.ticks < 1000) {
            g.drawImage(backgroundImage2.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);
        }

        else if (jumpyBirb.ticks >= 1000 && jumpyBirb.ticks < 1500) {
            g.drawImage(backgroundImage3.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);
        }

        else if (jumpyBirb.ticks >= 1500 && jumpyBirb.ticks < 2000) {
            g.drawImage(backgroundImage4.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);
        }

        else if (jumpyBirb.ticks >= 2000) {
            g.drawImage(backgroundImage5.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);
        }


        
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
