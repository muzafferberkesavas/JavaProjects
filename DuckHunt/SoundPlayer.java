import javafx.scene.media.AudioClip;

import java.io.File;

/**
 * This class a static class that takes necessary actions in order to create AudioClips that are going to be used
 * throughout the game.
 */
public class SoundPlayer {
    public static AudioClip titleTheme = new AudioClip(new File("src/assets/effects/Title.mp3").toURI().toString());
    public static AudioClip introTheme = new AudioClip(new File("src/assets/effects/Intro.mp3").toURI().toString());
    public static AudioClip duckFall = new AudioClip(new File("src/assets/effects/DuckFalls.mp3").toURI().toString());
    public static AudioClip levelComplete = new AudioClip(new File("src/assets/effects/LevelCompleted.mp3").toURI().toString());
    public static AudioClip gameOver = new AudioClip(new File("src/assets/effects/GameOver.mp3").toURI().toString());
    public static AudioClip gunshot = new AudioClip(new File("src/assets/effects/Gunshot.mp3").toURI().toString());
    public static AudioClip gameComplete = new AudioClip(new File("src/assets/effects/GameCompleted.mp3").toURI().toString());

    // Set the volume of AudioClips to wanted volume.
    static {
        titleTheme.setVolume(DuckHunt.VOLUME);
        introTheme.setVolume(DuckHunt.VOLUME);
        duckFall.setVolume(DuckHunt.VOLUME);
        levelComplete.setVolume(DuckHunt.VOLUME);
        gameOver.setVolume(DuckHunt.VOLUME);
        gunshot.setVolume(DuckHunt.VOLUME);
        gameComplete.setVolume(DuckHunt.VOLUME);
    }

    /**
     * Plays the AudioClip.
     * @param sound AudioClip to play
     */
    public static void playSound(AudioClip sound) {
        sound.play();
    }

    /**
     * Stops the AudioClip.
     * @param sound AudioClip to stop
     */
    public static void stopSound(AudioClip sound) {
        sound.stop();
    }
}