package se.yrgo;

import javax.swing.*;
import java.awt.*;

public class Birb {

    public Rectangle birbRect;
    public ImageIcon birbImage;

    public Birb() {
        birbImage = new ImageIcon("src/images/hampusSprite.gif");
        birbRect = new Rectangle(Window.frameWidth / 2 - 25, Window.frameHeight / 2 - 25, 50,  50);
    }
}
