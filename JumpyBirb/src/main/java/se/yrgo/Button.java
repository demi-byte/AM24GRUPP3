package se.yrgo;

import java.awt.Color;
import java.awt.Rectangle;

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
        if (highlighted) {
            color = Color.RED;
        }
        else {
            color = Color.BLUE;
        }
        
        return color;
    }

    public String buttonText() {
        return buttonText;
    }

    public Boolean isHighlighted() {
        return highlighted;
    }
}
