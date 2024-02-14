package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class Birb {

    public Rectangle birbRect;
    public ImageIcon birbImage;
    public JLabel birbLabel;
    public int yMovement;

    private JumpyBirb jumpyBirb;
    private Boolean falling;

    public Birb(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        yMovement = 10;
        falling = true;

        birbImage = new ImageIcon("src/images/hampusSprite.gif");
        birbRect = new Rectangle(Window.frameWidth / 2 - 25, Window.frameHeight / 2 - 25, 50,  50);
        birbLabel = new JLabel(birbImage);
        birbLabel.setBounds(100, 100, 101, 79);

        jumpyBirb.add(birbLabel);
    }

    public void jump() {

        falling = false;

        if (yMovement > 0) {
            yMovement *= -1;
        }

        birbRect.y += yMovement*10;

        falling = true;
    }

    public void fall() {

        if (jumpyBirb.birb.birbRect.y > Window.frameHeight - JumpyBirb.groundHeight) {
            jumpyBirb.gameOver = true;
        }

        if (birbRect.y + yMovement >= Window.frameHeight - JumpyBirb.groundHeight) {
            birbRect.y = Window.frameHeight - JumpyBirb.groundHeight - birbRect.height;
        }


        if (falling) {
            if (yMovement < 0) {
                yMovement *= -1;
            }

            birbRect.y += yMovement;
        }

    }

    public void paintBirb(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(birbRect.x, birbRect.y, birbRect.width, birbRect.height);
    }

}
