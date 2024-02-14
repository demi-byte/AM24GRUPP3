package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class Birb {

    public Rectangle birbRect;
    public ImageIcon birbImage;
    public JLabel birbLabel;
    public int yMovement;

    private JumpyBirb jumpyBirb;

    public Birb(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        birbImage = new ImageIcon("src/images/hampusSprite.gif");
        birbRect = new Rectangle(Window.frameWidth / 2 - 25, Window.frameHeight / 2 - 25, 50,  50);
        birbLabel = new JLabel(birbImage);
        birbLabel.setBounds(100, 100, 101, 79);

        jumpyBirb.add(birbLabel);
    }

    public void jump() {



    }

    public void fall() {

        if (yMovement > 0) {
            yMovement = 0;
        }

        yMovement -= 10;

        birbRect.y += yMovement;

    }

    public void paintBirb(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(birbRect.x, birbRect.y, birbRect.width, birbRect.height);
    }
}
