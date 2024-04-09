package se.yrgo;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMenu implements MouseListener {


    private Button playButton;
    private Button easyButton;
    private Button hardButton;
    private Button highscoreButton;
    private JumpyBirb jumpyBirb;


    public GameMenu(JumpyBirb jumpyBirb) { 
        this.jumpyBirb = jumpyBirb;
        playButton = new Button(new Rectangle(), Color.BLUE, "PLAY", false);
        easyButton = new Button(new Rectangle(), Color.BLUE, "EASY", true);
        hardButton = new Button(new Rectangle(), Color.BLUE, "HARD", false);
        highscoreButton = new Button(new Rectangle(), Color.BLUE, "HIGHSCORE", false);
    }

    public void paintMenu(Graphics g, ImageIcon backgroundImage) {
        g.drawImage(backgroundImage.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);

    }

    public void navigate() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
