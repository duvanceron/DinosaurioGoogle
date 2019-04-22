package dinosaurgoogle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChronometerDino implements Runnable {

    private int seconds;
    private String secondsS;
    Thread thChron;
    //userGame gameBoardInChro;

    public ChronometerDino() {
        seconds = 0;
        secondsS = "";
        //gameBoardInChro = new userGame();
    }

    public void startChronometer() {
        if (thChron == null) {
            thChron = new Thread(this);
            thChron.start();
        } else {
            thChron.resume();
        }
    }

    public void stopCrhonometer() {
        thChron.suspend();
    }

    @Override
    public void run() {
        while (true) {
            buildChron();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChronometerDino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buildChron() {
        seconds++;
        if (seconds >= 0) {
          
            secondsS = "000" + seconds;

        }
        if (seconds >= 10) {
            secondsS = "00" + seconds;

        }
        if (seconds >= 100) {
            secondsS = "0" + seconds;

        }
        if (seconds >= 1000) {
            secondsS = "" + seconds;
        }

    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getSecondsS() {
        return secondsS;
    }

    public void setSecondsS(String secondsS) {
        this.secondsS = secondsS;
    }

}
