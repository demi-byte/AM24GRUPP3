package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class Birb {

    public Rectangle birbRect;
    public Rectangle collisionBoxHead;
    public Rectangle collisionBoxBody;

    public ImageIcon birbImage;
    public JLabel birbLabel;
    public int yMovement;

    private JumpyBirb jumpyBirb;

    private final int gravity = 1;
    private final int jumpStrength = -15;

    public Birb(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        yMovement = 10;



        birbImage = new ImageIcon("src/images/nyhampusgif.gif");
        birbRect = new Rectangle(Window.frameWidth / 2 - 25, Window.frameHeight / 2 - 25, 152,  119);
        collisionBoxHead = new Rectangle(birbRect.x, birbRect.y, 50,  50);
        collisionBoxBody = new Rectangle(birbRect.x, birbRect.y, 100,  70);
        birbLabel = new JLabel(birbImage);
        birbLabel.setBounds(birbRect.x, birbRect.y, 122, 95);

        jumpyBirb.add(birbLabel);
    }

    public void jump() {
        if (birbRect.y > (jumpStrength*-1)) {
            yMovement = jumpStrength;
        }
    }

    public void update() {
       if (birbRect.y + yMovement >= Window.frameHeight) {
            jumpyBirb.gameOver = true;
       }

       
        yMovement += gravity;
        birbRect.y += yMovement;

        birbLabel.setLocation(birbRect.getLocation());

    }

    public void paintBirb(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(collisionBoxHead.x, collisionBoxHead.y, collisionBoxHead.width, collisionBoxHead.height);
    }

}
