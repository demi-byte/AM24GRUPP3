package org.example;

import javax.swing.*;
import java.awt.*;

public class MakeGraphics extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        JumpyBirb.jumpyBirb.repaint(g);
    }
}
