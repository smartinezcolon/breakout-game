package com.breakout;

import com.breakout.controller.GameController;
import com.breakout.model.GameModel;
import com.breakout.view.GameView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Breakout");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            GameModel model = new GameModel();
            GameView view = new GameView(model);
            // Controller registers itself as listener and starts the timer
            new GameController(model, view);
            
            // Adjust frame size to account for window borders
            frame.add(view);
            frame.getContentPane().setPreferredSize(new java.awt.Dimension(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT));
            frame.pack();
            
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            view.requestFocusInWindow();
        });
    }
}
