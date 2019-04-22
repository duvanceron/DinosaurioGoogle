package dinosaurgoogle;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class centerInitialL extends Canvas {

    topInitialL topInCenter;
    southInitialL southInCenter;
    Graphics2D buffer;
    Image imageOff, backgroundTop;
    Dimension dim;
    URL url;

    public centerInitialL() {
        url = getClass().getResource("/Images/dinoFriend.gif");
        backgroundTop = new ImageIcon(url).getImage();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundTop, 0, 0, getWidth(), getHeight(), this);
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

}
