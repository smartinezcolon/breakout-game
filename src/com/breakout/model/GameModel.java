package com.breakout.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private int score;
    private int lives;
    private GameState state;
    
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    
    private static final int ROWS = 5;
    private static final int COLS = 8;
    
    public GameModel() {
        initGame();
    }
    
    public void initGame() {
        score = 0;
        lives = 3;
        state = GameState.TITLE;
        
        paddle = new Paddle(GAME_WIDTH / 2 - 50, GAME_HEIGHT - 50);
        ball = new Ball(GAME_WIDTH / 2 - 8, GAME_HEIGHT - 50 - 16); 
        
        initBricks();
    }
    
    private void initBricks() {
        bricks = new ArrayList<>();
        Color[] rowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        int[] rowPoints = {50, 40, 30, 20, 10};
        
        int brickWidth = 80;
        int brickHeight = 30;
        int padding = 10;
        int offsetX = (GAME_WIDTH - (COLS * (brickWidth + padding) - padding)) / 2;
        int offsetY = 60;
        
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int bx = offsetX + c * (brickWidth + padding);
                int by = offsetY + r * (brickHeight + padding);
                bricks.add(new Brick(bx, by, brickWidth, brickHeight, rowColors[r], rowPoints[r]));
            }
        }
    }
    
    public void resetBallAndPaddle() {
        paddle.setX(GAME_WIDTH / 2 - paddle.getWidth() / 2);
        ball.setX(GAME_WIDTH / 2 - ball.getRadius());
        ball.setY(paddle.getY() - ball.getRadius() * 2);
        ball.stop();
        state = GameState.START;
    }

    public void launchBall() {
        if (state == GameState.START) {
            state = GameState.PLAYING;
            ball.launch();
        }
    }

    public void movePaddleLeft() {
        paddle.setDx(-paddle.getSpeed());
    }

    public void movePaddleRight() {
        paddle.setDx(paddle.getSpeed());
    }

    public void stopPaddle() {
        paddle.setDx(0);
    }
    
    public void loseLife() {
        lives--;
        if (lives <= 0) {
            state = GameState.GAME_OVER;
        } else {
            resetBallAndPaddle();
        }
    }
    
    public void checkWinCondition() {
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                return;
            }
        }
        state = GameState.VICTORY;
    }
    
    // Getters and setters
    public int getScore() { return score; }
    public void addScore(int points) { score += points; }
    public int getLives() { return lives; }
    public GameState getState() { return state; }
    public void setState(GameState state) { this.state = state; }
    public Paddle getPaddle() { return paddle; }
    public Ball getBall() { return ball; }
    public List<Brick> getBricks() { return bricks; }
}
