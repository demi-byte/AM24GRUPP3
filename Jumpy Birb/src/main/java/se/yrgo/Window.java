package se.yrgo;

import javax.swing.*;

public class Window extends JFrame {

    public static int frameWidth = 1200;
    public static int frameHeight = 600;

    public Window() {
        this.add(new JumpyBirb());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setTitle("Jumpy Birb");
        this.setResizable(false);
        this.setVisible(true);

    }
}
