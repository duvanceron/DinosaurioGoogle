/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinosaurgoogle;

import java.applet.AudioClip;

/**
 *
 * @author Dceron
 */
public class soundApplet {

    AudioClip sound;

    public static void main(String[] args) {
        new soundApplet();
    }

    public soundApplet() {
        sound = java.applet.Applet.newAudioClip(getClass().getResource("/Sounds/loseDino.wav"));
        sound.play();
    }

}
