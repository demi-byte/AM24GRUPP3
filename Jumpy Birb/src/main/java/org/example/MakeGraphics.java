package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * This is a JPanel object. It is called at the end of the actionPerformer in
 * the JumpyBirb class.
 *
 * */
public class MakeGraphics extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        JumpyBirb.jumpyBirb.repaint(g);
    }
}
