package dinosaurgoogle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Chronometer implements Runnable {

    Integer minuts, seconds, thousandths;
    String min, sec, tho;
    boolean chronometerActive;
    Thread threadChronometer;

    public Chronometer() {

    }

    public void buildChronometer() throws InterruptedException {

        minuts = seconds = thousandths = 0;
        min = sec = tho = "";
        while (chronometerActive) {
            //Thread.sleep(4);
            thousandths += 4;
            if (thousandths == 1000) {
                thousandths = 0;
                seconds += 1;
                if (seconds == 60) {
                    seconds = 0;
                    minuts++;

                }

            }

            if (minuts < 10) {
                min = "0" + minuts;
            } else {
                min = minuts.toString();
            }
            if (seconds < 10) {
                sec = "0" + seconds;
            } else {
                sec = seconds.toString();
            }
            if (thousandths < 10) {
                tho = "00" + thousandths;

            } else if (thousandths < 100) {
                tho = "0" + thousandths;
            } else {
                tho = thousandths.toString();
            }
            System.out.println(min + ":" + sec + ":" + tho);

        }

    }

    public void startChronometer() {
        chronometerActive = true;
        threadChronometer = new Thread(this);
        threadChronometer.start();

    }

    public void stopChronometer() {
        chronometerActive = false;

    }

    @Override
    public void run() {
        try {
            buildChronometer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Chronometer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}