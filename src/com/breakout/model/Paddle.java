package com.breakout.model;

public class Paddle {
    private int x, y, width, height;
    private int dx;
    private final int speed = 7;

    public Paddle(int startX, int startY) {
        this.width = 100;
        this.height = 15;
        this.x = startX;
        this.y = startY;
        this.dx = 0;
    }

    public void move() {
        x += dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getSpeed() { return speed; }
}
