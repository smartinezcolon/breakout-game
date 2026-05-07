package com.breakout.view;

import com.breakout.model.Ball;
import com.breakout.model.Brick;
import com.breakout.model.GameModel;
import com.breakout.model.GameState;
import com.breakout.model.Paddle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GameView extends JPanel {
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawHUD(g2d);
        drawBricks(g2d);
        drawPaddle(g2d);
        drawBall(g2d);
        drawOverlays(g2d);
    }

    private void drawHUD(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + model.getScore(), 20, 30);
        g2d.drawString("Lives: " + model.getLives(), GameModel.GAME_WIDTH - 100, 30);
    }

    private void drawBricks(Graphics2D g2d) {
        for (Brick brick : model.getBricks()) {
            if (!brick.isDestroyed()) {
                g2d.setColor(brick.getColor());
                g2d.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                g2d.setColor(Color.BLACK); // Outline
                g2d.drawRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            }
        }
    }

    private void drawPaddle(Graphics2D g2d) {
        Paddle paddle = model.getPaddle();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
    }

    private void drawBall(Graphics2D g2d) {
        Ball ball = model.getBall();
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);
    }

    private void drawOverlays(Graphics2D g2d) {
        GameState state = model.getState();
        String message = "";
        String subMessage = "";
        
        if (state == GameState.TITLE) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 80));
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (GameModel.GAME_WIDTH - metrics.stringWidth("BREAKOUT")) / 2;
            int y = GameModel.GAME_HEIGHT / 2 - 40;
            g2d.drawString("BREAKOUT", x, y);
            
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            metrics = g2d.getFontMetrics();
            int subX = (GameModel.GAME_WIDTH - metrics.stringWidth("Press SPACE to start")) / 2;
            g2d.drawString("Press SPACE to start", subX, y + 60);
            return;
        }
        
        if (state == GameState.START) {
            message = "Press SPACE to Launch";
        } else if (state == GameState.PAUSED) {
            message = "PAUSED";
            subMessage = "P: Resume | R: Restart | M: Main Menu";
        } else if (state == GameState.RESUMING) {
            int seconds = (int) Math.ceil(model.getResumeCountdown() / 60.0);
            message = String.valueOf(seconds);
        } else if (state == GameState.GAME_OVER) {
            message = "Game Over";
            subMessage = "R: Restart | M: Main Menu";
        } else if (state == GameState.VICTORY) {
            message = "You Win!";
            subMessage = "R: Restart | M: Main Menu";
        }

        if (!message.isEmpty()) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (GameModel.GAME_WIDTH - metrics.stringWidth(message)) / 2;
            int y = GameModel.GAME_HEIGHT / 2;
            g2d.drawString(message, x, y);
            
            if (!subMessage.isEmpty()) {
                g2d.setFont(new Font("Arial", Font.PLAIN, 20));
                metrics = g2d.getFontMetrics();
                int subX = (GameModel.GAME_WIDTH - metrics.stringWidth(subMessage)) / 2;
                g2d.drawString(subMessage, subX, y + 40);
            }
        }
    }
}
