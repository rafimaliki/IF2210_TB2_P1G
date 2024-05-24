package tb2.p1g.harvestmooncombat.Audio;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioFactory {

    static Map<String, String> audioMap = new HashMap<>();
    private static MediaPlayer currentMediaPlayer;

    public static void addAudio(String key, String path) {
        audioMap.put(key, path);
    }

    public static void playAudio(String key) {
        stopAudio();
        String path = audioMap.get(key);
        if (path != null) {
            if (currentMediaPlayer != null) {
                currentMediaPlayer.stop();
            }
            Media media = new Media(path);
            currentMediaPlayer = new MediaPlayer(media);
            currentMediaPlayer.setAutoPlay(true);
            currentMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } else {
            System.out.println("Audio " + key + " not found");
        }
    }

    public static void stopAudio() {
        if (currentMediaPlayer != null) {
            currentMediaPlayer.stop();
            currentMediaPlayer = null;
        }
    }
}
