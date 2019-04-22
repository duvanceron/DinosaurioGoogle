package dinosaurgoogle;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ScreenIntro extends Canvas implements Runnable {

    private Image screen, level1, level2, level3, level4, level5, imageOff;
    private Dimension dim;
    private Graphics2D buffer;
    private Thread Thscreen;
    private int posX, PosY, posX2, posY2, nivelPlayer;
    private Dimension size;
    windowInitial windowInScreen;

    public ScreenIntro() {
        screen = new ImageIcon(getClass().getResource("/Images/screen.jpg")).getImage();
        level1 = new ImageIcon(getClass().getResource("/Images/nivel1.jpg")).getImage();
        level2 = new ImageIcon(getClass().getResource("/Images/nivel2.jpg")).getImage();
        level3 = new ImageIcon(getClass().getResource("/Images/nivel3.jpg")).getImage();
        level4 = new ImageIcon(getClass().getResource("/Images/nivel4.jpg")).getImage();
        level5 = new ImageIcon(getClass().getResource("/Images/nivel5.jpg")).getImage();
        size = new Dimension(650, 700);
        this.setPreferredSize(size);
        nivelPlayer = 1;
        posX = PosY = posX2 = 0;
        posY2 = (size.height / 2);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        paintLevel(g2);
        g2.drawImage(screen, posX, PosY, getWidth(), (getHeight() / 2), this);
        g2.drawImage(screen, posX2, posY2, getWidth(), (getHeight() / 2), this);
        repaint();
    }

    @Override
    public void update(Graphics g) {
        dim = getSize();
        imageOff = createImage(dim.width, dim.height);
        buffer = (Graphics2D) imageOff.getGraphics();
        buffer.setColor(getBackground());
        paint(buffer);
        g.drawImage(imageOff, 0, 0, this);

    }

    public void start() {
        if (Thscreen == null) {
            Thscreen = new Thread(this);
            Thscreen.start();
        } else {
            Thscreen.resume();
        }
    }

    public void stop() {

        if (PosY == (-size.height / 2)) {
            windowInScreen.showCanvasTrack();
            posX = PosY = posX2 = 0;
            posY2 = (size.height / 2);
            Thscreen.suspend();

            //aqui llamar el metodo de mostrar el juego
        }
    }

    @Override
    public void run() {
        while (true) {
            PosY--;
            posY2++;
            stop();
            try {
                Thscreen.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenIntro.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void paintLevel(Graphics2D g) {
        switch (nivelPlayer) {
            case 1:
                g.drawImage(level1, 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                g.drawImage(level2, 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                g.drawImage(level3, 0, 0, getWidth(), getHeight(), this);
                break;
            case 4:
                g.drawImage(level4, 0, 0, getWidth(), getHeight(), this);
                break;
            case 5:
                g.drawImage(level5, 0, 0, getWidth(), getHeight(), this);
                break;     
                
        }

    }

    public int getNivelPlayer() {
        return nivelPlayer;
    }

    public void setNivelPlayer(int nivelPlayer) {
        this.nivelPlayer = nivelPlayer;
    }

}
