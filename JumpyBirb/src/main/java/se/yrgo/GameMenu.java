package se.yrgo;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMenu implements MouseListener {

    public class Button {
        private Rectangle shapePosition;
        private Color color;
        private String buttonText;
        private Boolean highlighted;

        public Button(Rectangle shapePosition, Color color, String buttonText, Boolean highlighted) {
            this.shapePosition = shapePosition;
            this.color = color;
            this.buttonText = buttonText;
            this.highlighted = highlighted;

        }

        public void highlight(Boolean highlighted) {
            this.highlighted = highlighted;
        }

        public Rectangle getShapePosition() {
            return shapePosition;
        }

        public Color getColor() {
            return color;
        }

        public String buttonText() {
            return buttonText;
        }

        public Boolean isHighlighted() {
            return highlighted;
        }
    }

    private Rectangle playButton;
    private Rectangle easyButton;
    private Rectangle hardButton;
    private Rectangle highscoresButton;
    private JumpyBirb jumpyBirb;

    public GameMenu(JumpyBirb jumpyBirb) {
        this.jumpyBirb = jumpyBirb;
        playButton = new Rectangle();
        easyButton = new Rectangle();
        hardButton = new Rectangle();
        highscoresButton = new Rectangle();
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
