package dinosaurgoogle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound implements Runnable {

    Thread thSound;
    Player song;
    static boolean pausa;

    public Sound(String route) throws FileNotFoundException, JavaLayerException, InterruptedException {
        pausa = false;
        song = new Player(new FileInputStream(route));
        start();
    }

    public void start() {
        if (thSound == null) {
            thSound = new Thread(this);
            thSound.start();
        } else {
            thSound.resume();
        }
    }

    @Override
    public void run() {
        try {
            while (!pausa) {
                if (!song.play(1)) {
                    break;
                }
            }
        } catch (Exception e) {
        }

        // pausa = true;
    }
}
