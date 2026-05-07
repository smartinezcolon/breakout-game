package com.breakout.controller;

import com.breakout.model.Ball;
import com.breakout.model.Brick;
import com.breakout.model.GameModel;
import com.breakout.model.GameState;
import com.breakout.model.Paddle;
import com.breakout.view.GameView;
import com.breakout.util.SoundManager;

import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter implements ActionListener {
    private GameModel model;
    private GameView view;
    private Timer timer;
    private SoundManager soundManager;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.soundManager = new SoundManager();
        
        view.addKeyListener(this);
        timer = new Timer(16, this); // ~60fps
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getState() == GameState.PLAYING || model.getState() == GameState.START) {
            updateGame();
            view.repaint();
        } else if (model.getState() == GameState.GAME_OVER || model.getState() == GameState.VICTORY || model.getState() == GameState.TITLE) {
            view.repaint(); // Just to draw the overlay
        }
    }

    private void updateGame() {
        Paddle paddle = model.getPaddle();
        Ball ball = model.getBall();

        // Move paddle
        paddle.move();

        // Keep paddle in bounds
        if (paddle.getX() < 0) {
            paddle.setX(0);
        } else if (paddle.getX() + paddle.getWidth() > GameModel.GAME_WIDTH) {
            paddle.setX(GameModel.GAME_WIDTH - paddle.getWidth());
        }

        // If game is START, ball follows paddle
        if (model.getState() == GameState.START) {
            ball.setX(paddle.getX() + paddle.getWidth() / 2 - ball.getRadius());
            return;
        }

        // Move ball
        ball.move();

        // Check wall collisions
        if (ball.getX() <= 0) {
            ball.setDx(Math.abs(ball.getDx())); // Bounce right
        } else if (ball.getX() + ball.getRadius() * 2 >= GameModel.GAME_WIDTH) {
            ball.setDx(-Math.abs(ball.getDx())); // Bounce left
        }

        if (ball.getY() <= 0) {
            ball.setDy(Math.abs(ball.getDy())); // Bounce down
        }

        // Check bottom collision (lose life)
        if (ball.getY() >= GameModel.GAME_HEIGHT) {
            model.loseLife();
            return;
        }

        // Paddle collision
        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);
        Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

        if (ballRect.intersects(paddleRect) && ball.getDy() > 0) { // Only bounce if moving down
            // Fix position so it doesn't get stuck
            ball.setY(paddle.getY() - ball.getRadius() * 2);
            
            // Calculate hit position for bounce angle
            int hitPoint = ball.getX() + ball.getRadius() - paddle.getX();
            double relativeHit = (hitPoint - (paddle.getWidth() / 2.0)) / (paddle.getWidth() / 2.0);
            // relativeHit is between -1.0 (left edge) and 1.0 (right edge)
            
            double speed = ball.getSpeed();
            // Maximum angle of 60 degrees (in radians)
            double angle = relativeHit * (Math.PI / 3);
            
            ball.setDx(speed * Math.sin(angle));
            ball.setDy(-speed * Math.cos(angle));
        }

        // Brick collision
        for (Brick brick : model.getBricks()) {
            if (!brick.isDestroyed()) {
                Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                if (ballRect.intersects(brickRect)) {
                    brick.setDestroyed(true);
                    model.addScore(brick.getPoints());
                    model.checkWinCondition();
                    soundManager.playBrickHit();
                    
                    // Simple bounce logic: mostly bounce vertically unless hitting sides
                    int ballCenter = ball.getX() + ball.getRadius();
                    if (ballCenter < brick.getX() || ballCenter > brick.getX() + brick.getWidth()) {
                        ball.setDx(-ball.getDx());
                    } else {
                        ball.setDy(-ball.getDy());
                    }
                    break; // Only break one brick per frame
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            model.movePaddleLeft();
        } else if (key == KeyEvent.VK_RIGHT) {
            model.movePaddleRight();
        } else if (key == KeyEvent.VK_SPACE) {
            if (model.getState() == GameState.TITLE) {
                model.setState(GameState.START);
            } else {
                model.launchBall();
            }
        } else if (key == KeyEvent.VK_R) {
            if (model.getState() == GameState.GAME_OVER || model.getState() == GameState.VICTORY) {
                model.initGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Only stop if the released key matches the current direction of the paddle.
        // For simplicity:
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            model.stopPaddle();
        }
    }
}
