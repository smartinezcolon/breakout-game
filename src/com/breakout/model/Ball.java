package com.breakout.model;

public class Ball {
    private int x, y, radius;
    private double dx, dy;
    private final double speed = 6.0;

    public Ball(int startX, int startY) {
        this.radius = 8;
        this.x = startX;
        this.y = startY;
        this.dx = 0;
        this.dy = 0;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void launch() {
        // Launch upwards and slightly to the right
        dx = speed * 0.5;
        dy = -speed;
    }

    public void stop() {
        dx = 0;
        dy = 0;
    }
    
    public double getSpeed() { return speed; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getRadius() { return radius; }
    public double getDx() { return dx; }
    public void setDx(double dx) { this.dx = dx; }
    public double getDy() { return dy; }
    public void setDy(double dy) { this.dy = dy; }
}
