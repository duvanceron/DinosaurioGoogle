package dinosaurgoogle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class dinoRex implements Runnable {

    private int posx, posy, heightD, widthD, lifes;
    private double speedDino, speedClone;
    private Image rex;
    private Rectangle2D rectHead, rectFoot;
    private boolean dinoJump;
    CanvasTrack trackInD;
    Thread thDino;

    public dinoRex(int posY, CanvasTrack track) {
        this.posx = 0;
        this.posy = posY;
        trackInD = track;
        lifes = 3;
        speedDino = 3;
        System.out.println("Velocidad"+speedDino);
        speedClone = speedDino;
        this.rex = new ImageIcon(getClass().getResource("/Images/raptor3.gif")).getImage();
        dinoJump = true;
        widthD = rex.getWidth(null);
        heightD = rex.getHeight(null);

    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public Image getRex() {
        return rex;
    }

    public void setRex(Image rex) {
        this.rex = rex;
    }

    public int getHeightD() {
        return heightD;
    }

    public void setHeightD(int heightD) {
        this.heightD = heightD;
    }

    public int getWidthD() {
        return widthD;
    }

    public void setWidthD(int widthD) {
        this.widthD = widthD;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public Rectangle2D getRectHead() {
        return rectHead;
    }

    public void setRectHead(Rectangle2D rectHead) {
        this.rectHead = rectHead;
    }

    public Rectangle2D getRectFoot() {
        return rectFoot;
    }

    public void setRectFoot(Rectangle2D rectFoot) {
        this.rectFoot = rectFoot;
    }

    public boolean isDinoJump() {
        return dinoJump;
    }

    public void setDinoJump(boolean dinoJump) {
        this.dinoJump = dinoJump;
    }

    public double getSpeedDino() {
        return speedDino;
    }

    public void setSpeedDino(double speedDino) {
        this.speedDino = speedDino;
    }

    public double getSpeedClone() {
        return speedClone;
    }

    public void setSpeedClone(double speedClone) {
        this.speedClone = speedClone;
    }
    
    
    
    public void start() {
        if (thDino == null) {
            thDino = new Thread(this);
            thDino.start();
        } else {
            thDino.resume();

        }
    }

    public void stop() {
        thDino.suspend();
    }

    public void jumpDino() {
        if (getPosy() >= (trackInD.getSizeCanvas().height - 430)) {
            setPosy(getPosy() - 1);
            rex = new ImageIcon(getClass().getResource("/Images/raptor4.png")).getImage();
//            if (getPosy() <= (trackInD.getSizeCanvas().height - 415)) {
//                speedDino = 5;
//
//            }
        } else {
            dinoJump = false;

        }

    }

    public void downDino() {
        if (posy <= (trackInD.getSizeCanvas().height - 241)) {
            posy++;
            speedDino = speedClone;
        } else {
            dinoJump = false;
            rex = new ImageIcon(getClass().getResource("/Images/raptor3.gif")).getImage();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (dinoJump == true) {

                jumpDino();
            } else {
                downDino();
            }
            try {
                thDino.sleep((long) speedDino);
            } catch (InterruptedException ex) {
                Logger.getLogger(dinoRex.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    void paintDino(Graphics2D g2) {
        g2.drawImage(getRex(), getPosx(), getPosy(), null);
    }

    void createRectangleDino(Graphics2D g2) {
        rectHead = new Rectangle2D.Float(getPosx() + 40, getPosy() + 30, getWidthD() - 55, getHeightD() - 75);
        rectFoot = new Rectangle2D.Float(getPosx() + 40, getPosy() + 70, getWidthD() - 80, getHeightD() - 70);
       // g2.draw(rectHead);
      //  g2.draw(rectFoot);
    }

}
