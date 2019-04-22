/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinosaurgoogle;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Dceron
 */
public class topInitialL extends Canvas {

    centerInitialL centerInTop;
    southInitialL southInTop;

    Graphics2D buffer;
    Image imageOff, backgroundTop;
    Dimension dim;
    URL url;

    public topInitialL() {
        url = getClass().getResource("/Images/rexLogo4.png");
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
