package service;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    public final MediaPlayer backgroundMusicPlayer;
    public final AudioClip connectionPlayer;
    public final AudioClip collisionPlayer;
    private static AudioManager audioManager;

    public AudioManager() {
        this.backgroundMusicPlayer = createMediaPlayer("/music.wav");
        this.connectionPlayer = createAudioClip("/connection.mp3");
        this.collisionPlayer = createAudioClip("/packet_damage.mp3");

        double volume = File.getFromMap("volume");
        this.backgroundMusicPlayer.setVolume(volume);
        this.connectionPlayer.setVolume(volume);
        this.collisionPlayer.setVolume(volume);
        this.backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
        audioManager = this;
    }


    // ... createAudioClip and createMediaPlayer methods remain the same ...
    private AudioClip createAudioClip(String resourcePath) {
        // The user specifically asked for this method.
        String url = getClass().getResource(resourcePath).toExternalForm();
        AudioClip clip = new AudioClip(url);
        clip.setVolume(0.5); // Default volume for sound effects
        return clip;
    }

    /**
     * Creates a MediaPlayer for longer music tracks.
     * @param resourcePath The path to the audio file, starting from the resources root (e.g., "/music.wav").
     * @return A configured MediaPlayer object.
     */
    private MediaPlayer createMediaPlayer(String resourcePath) {
        // The user specifically asked for this method.
        String url = getClass().getResource(resourcePath).toExternalForm();
        Media media = new Media(url);
        return new MediaPlayer(media);
    }
    /**
     * Sets the volume for all sound effects (AudioClips).
     * @param volume A value between 0.0 and 1.0.
     */
    public void setSoundEffectsVolume(double volume) {
        connectionPlayer.setVolume(volume);
        collisionPlayer.setVolume(volume);
    }

    public void playBackgroundMusic() {
        backgroundMusicPlayer.play();
    }
    public static void playConnection(){
        audioManager.connectionPlayer.play();
    }
    public static void playCollison(){
        audioManager.connectionPlayer.play();
    }

}
