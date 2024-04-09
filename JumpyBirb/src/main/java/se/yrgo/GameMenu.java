package se.yrgo;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class GameMenu implements MouseListener, MouseMotionListener {

    private Button easyButton;
    private Button hardButton;
    private Button highscoreButton;
    private List<Button> buttons;
    private JumpyBirb jumpyBirb;

    public GameMenu(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;
        easyButton = new Button(new Rectangle(Window.frameWidth/2, 100, 150, 100), Color.BLUE, "EASY", false);
        hardButton = new Button(new Rectangle(Window.frameWidth/2, 250, 150, 100), Color.BLUE, "HARD", false);
        highscoreButton = new Button(new Rectangle(Window.frameWidth/2, 400, 150, 100), Color.BLUE, "HIGHSCORE", false);
        buttons = List.of(easyButton, hardButton, highscoreButton);
    }

    public void paintMenu(Graphics g, ImageIcon backgroundImage) {
        g.drawImage(backgroundImage.getImage(), 0, 0, jumpyBirb.getWidth(), jumpyBirb.getHeight(), jumpyBirb);

        for (Button button : buttons) {
            g.setColor(button.getColor());
            g.drawRect((int)button.getShapePosition().getX(), (int)button.getShapePosition().getY(), (int)button.getShapePosition().getWidth(), (int)button.getShapePosition().getHeight());
        }
    
        jumpyBirb.birb.birbRect.x = 300;
        jumpyBirb.birb.birbRect.y = 300;

    }

    public void navigate() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (easyButton.getShapePosition().contains(e.getPoint())) {

        }

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
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        for (Button button : buttons) {
            if (button.getShapePosition().contains(e.getPoint())) {
                button.highlight(true);
            } else {
                button.highlight(false);
            }
        }
    }
}
