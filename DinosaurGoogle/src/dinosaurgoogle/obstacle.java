package dinosaurgoogle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class obstacle {

    private int posx, posy, widthC, HeightC;
    private Image cactus;
    private Thread thObstacle;
    private CanvasTrack track;
    private Rectangle2D rectObstacle;
    //CanvasTrack trackInO;

    public obstacle(int posx, int posy, CanvasTrack trackC) {
        this.posx = posx;
        this.posy = posy;
        this.track = trackC;
        this.cactus = new ImageIcon(getClass().getResource("/Images/cactus3.png")).getImage();
        widthC = cactus.getWidth(null);
        HeightC = cactus.getHeight(null);

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

    public Image getCactus() {
        return cactus;
    }

    public void setCactus(Image cactus) {
        this.cactus = cactus;
    }

    public Thread getThObstacle() {
        return thObstacle;
    }

    public void setThObstacle(Thread thObstacle) {
        this.thObstacle = thObstacle;
    }

    public int getWidthC() {
        return widthC;
    }

    public void setWidthC(int widthC) {
        this.widthC = widthC;
    }

    public int getHeightC() {
        return HeightC;
    }

    public void setHeightC(int HeightC) {
        this.HeightC = HeightC;
    }

    public Rectangle2D getRectObstacle() {
        return rectObstacle;
    }

    public void setRectObstacle(Rectangle2D rectObstacle) {
        this.rectObstacle = rectObstacle;
    }
    

    public void paintObstacles(Graphics2D g2, int i) {
        g2.drawImage(track.getObstacles().get(i).getCactus(), track.getObstacles().get(i).getPosx(), track.getObstacles().get(i).getPosy(), null);

    }

    public void createRectangleObstacle(Graphics2D g2,int i) {
        rectObstacle = new Rectangle2D.Float(track.getObstacles().get(i).getPosx() + 5,track.getObstacles().get(i).getPosy() + 5, track.getObstacles().get(i).getWidthC() - 5,track.getObstacles().get(i).getWidthC() - 5);
      //  g2.draw(rectObstacle);
    }

}
