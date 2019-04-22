/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinosaurgoogle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author Dceron
 */
public class windowInitial extends JFrame {

    Container containerWindow;
    topInitialL top;
    centerInitialL center;
    southInitialL south;
    JPanel JpanelContainer, containerCanvas;
    CanvasTrack track;
    dinoRex rex;
    userGame user;
    ScreenIntro intro;

    public windowInitial(String title) {
        super(title);
        setBounds(100, 0, 650, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        JpanelContainer = new JPanel();
        containerCanvas = new JPanel();
        top = new topInitialL();
        center = new centerInitialL();
        south = new southInitialL();
        track = new CanvasTrack();
        user = new userGame();
        intro = new ScreenIntro();
        containerWindowIni();

    }

    public final void containerWindowIni() {

        containerWindow = this.getContentPane();
        containerWindow.setBackground(Color.black);

        JpanelContainer.setLayout(new GridLayout(3, 1, 10, 10));
        JpanelContainer.setBackground(Color.black);

        containerCanvas.setLayout(new BorderLayout());

        south.centerInSouth = this.center;
        south.windowInSouth = this;
        intro.windowInScreen = this;
        track.window = this;
        track.GameBoard = this.user;
        JpanelContainer.add(top);
        JpanelContainer.add(center);
        JpanelContainer.add(south);
        containerWindow.add(JpanelContainer);
        // showCanvasTrack();
        // showIntro();
    }

    public void showIntro() {
        containerCanvas.setVisible(false);
        JpanelContainer.setVisible(false);
        intro.start();
        containerWindow.add(intro);
         intro.setVisible(true);
    }

    public void showCanvasTrack() {

        intro.setVisible(false);
        JpanelContainer.setVisible(false);
        containerCanvas.add(user, BorderLayout.NORTH);
        containerCanvas.add(track, BorderLayout.CENTER);
        track.startTrack();
        track.chronometer.startChronometer();
        containerWindow.add(containerCanvas);
        containerCanvas.setVisible(true);
        track.setFocusable(true);

    }

}
