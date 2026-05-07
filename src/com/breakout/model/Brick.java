package com.breakout.model;

import java.awt.Color;

public class Brick {
    private int x, y, width, height;
    private Color color;
    private int points;
    private boolean isDestroyed;

    public Brick(int x, int y, int width, int height, Color color, int points) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.points = points;
        this.isDestroyed = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }
    public int getPoints() { return points; }
    public boolean isDestroyed() { return isDestroyed; }
    public void setDestroyed(boolean destroyed) { isDestroyed = destroyed; }
}
