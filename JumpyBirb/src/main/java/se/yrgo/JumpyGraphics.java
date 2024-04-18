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
            g.drawString("Press space to start!", 100, Window.frameHeight / 2 -20);
        }

        if (jumpyBirb.gameOver) {
            g.drawString("Game Over, you suck!", 100, Window.frameHeight / 2 -20);
        }

        g.setFont(new Font("Helvetica", 1, 40));
        g.drawString(Integer.toString(jumpyBirb.score), 80, 90);
        g.drawString(jumpyBirb.getHighestScore(), Window.frameWidth-100, 90);
    }


}
