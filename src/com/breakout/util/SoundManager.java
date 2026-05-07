package com.breakout.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip brickHitClip;

    public SoundManager() {
        try {
            File soundFile = new File("assets/brick_hit.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            brickHitClip = AudioSystem.getClip();
            brickHitClip.open(audioIn);
        } catch (Exception e) {
            System.err.println("Could not load sound: " + e.getMessage());
        }
    }

    public void playBrickHit() {
        if (brickHitClip != null) {
            brickHitClip.setFramePosition(0);
            brickHitClip.start();
        }
    }
}
