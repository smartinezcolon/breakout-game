package com.breakout.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip brickHitClip;
    private Clip lifeLostClip;
    private Clip gameOverClip;

    public SoundManager() {
        brickHitClip = loadClip("assets/brick_hit.wav");
        lifeLostClip = loadClip("assets/life_lost.wav");
        gameOverClip = loadClip("assets/game_over.wav");
    }

    private Clip loadClip(String filename) {
        try {
            File soundFile = new File(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (Exception e) {
            System.err.println("Could not load sound " + filename + ": " + e.getMessage());
            return null;
        }
    }

    public void playBrickHit() {
        playClip(brickHitClip);
    }

    public void playLifeLost() {
        playClip(lifeLostClip);
    }

    public void playGameOver() {
        playClip(gameOverClip);
    }

    private void playClip(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
