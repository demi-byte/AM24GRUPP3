package se.yrgo;

import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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

    
    private AudioInputStream aisJump;
    private AudioInputStream aisDeath;
    private Clip clipJump;
    public Clip clipDeath;

    public Birb(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;

        yMovement = 10;

        try {
            aisJump = AudioSystem.getAudioInputStream(new File("src/lib/jump.wav"));
            clipJump = AudioSystem.getClip();
            clipJump.open(aisJump);

            aisDeath = AudioSystem.getAudioInputStream(new File("src/lib/death.wav"));
            clipDeath = AudioSystem.getClip();
            clipDeath.open(aisDeath);
        }

        catch (IOException ioe) {
            System.out.println("io exception");
        }

        catch (UnsupportedAudioFileException uafe) {
            System.out.println("Unsupported audio");
        }

        catch (LineUnavailableException lue) {
            System.out.println("Line unavaiable");
        }

        birbImage = new ImageIcon("src/images/nyhampusgif.gif");
        birbRect = new Rectangle(Window.frameWidth / 2 - 25, Window.frameHeight / 2 - 25, 152, 119);
        birbLabel = new JLabel(birbImage);
        birbLabel.setBounds(birbRect.x, birbRect.y, 122, 95);

        jumpyBirb.add(birbLabel);
    }

    public void jump() {
        if (clipJump != null) {
            clipJump.setFramePosition(0);
            clipJump.start();
        }

        if (birbRect.y > (jumpStrength * -1)) {
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
        // g.setColor(Color.red);
        // g.fillRect(collisionBoxHead.x, collisionBoxHead.y, collisionBoxHead.width,
        // collisionBoxHead.height);
    }

}
