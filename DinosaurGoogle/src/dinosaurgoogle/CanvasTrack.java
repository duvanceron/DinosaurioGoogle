package dinosaurgoogle;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;

public class CanvasTrack extends Canvas implements KeyListener, Runnable {

    userGame GameBoard;
    windowInitial window;
    dinoRex dino;
    private Image imageOff, track, back;
    private Graphics2D buffer;
    private Dimension dim, sizeCanvas;
    private URL url;
    private Thread thTrack;
    private int posx, posx2, createRandom, nroObstacle, nroObstacleJump, countTime;
    private ArrayList<obstacle> obstacles;
    private obstacle obstacle;
    ChronometerDino chronometer;
    private double speedTrack;
    private Sound singLose;

    public CanvasTrack() {
        url = getClass().getResource("/Images/fondo.png");
        track = new ImageIcon(url).getImage();
        back = new ImageIcon(getClass().getResource("/Images/back4.png")).getImage();
        chronometer = new ChronometerDino();
        this.requestFocus();
        posx = 0;
        countTime = 0;
        nroObstacle = -1;
        nroObstacleJump = -1;
        speedTrack = 5;
        sizeCanvas = new Dimension(650, 700);
        System.out.println("dino");
        dino = new dinoRex((sizeCanvas.height - 240), this);
        this.setPreferredSize(sizeCanvas);
        posx2 = (sizeCanvas.width - 10);
        createRandom = 0;
        obstacles = new ArrayList();
        generateObstacles();
        addKeyListener(this);
        setFocusable(true);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawImage(back, posx, 0, getWidth(), getHeight(), this);
        g2.drawImage(back, posx2, 0, getWidth(), getHeight(), this);
        g2.drawImage(track, posx, 0, getWidth(), getHeight(), this);
        g2.drawImage(track, posx2, 0, getWidth(), getHeight(), this);
        dino.paintDino(g2);
        verefyIntersection(g2);
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

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            startTrack();
            chronometer.startChronometer();
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            dino.start();
            if (dino.getPosy() == (sizeCanvas.height - 240)) {
                dino.setDinoJump(true);
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void startTrack() {

        if (thTrack == null) {
            thTrack = new Thread(this);
            thTrack.start();

        } else {
            thTrack.resume();
        }
    }

    public void pauseTrack() {
        thTrack.suspend();

    }

    @Override
    public void run() {
        while (true) {
            restartTrack();
            posx--;
            posx2--;
            for (int i = 0; i < obstacles.size(); i++) {
                obstacles.get(i).setPosx(obstacles.get(i).getPosx() - 1);

            }
            try {
                Thread.sleep((long) speedTrack);
            } catch (InterruptedException ex) {
                Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (chronometer.getSeconds() % 5 == 0 && countTime < chronometer.getSeconds()) {
                countTime = chronometer.getSeconds();
                GameBoard.setScorePlayer(GameBoard.getScorePlayer() + 10);
                GameBoard.getJscore().setText("Score: " + GameBoard.getScorePlayer());

            }
            changeLevel();
            repaint();
        }
    }

    public void restartTrack() {
        if (posx == -640) {
            posx = (posx * -1);
        }
        if (posx2 == -640) {
            posx2 = (posx2 * -1);
        }
    }

    public Dimension getSizeCanvas() {
        return sizeCanvas;
    }

    public void setSizeCanvas(Dimension sizeCanvas) {
        this.sizeCanvas = sizeCanvas;
    }

    public void generateObstacles() {

        for (int i = 1200; i < 100000; i += 400) {
            createRandom = (int) (Math.random() * 2);

            if (createRandom == 1) {

                obstacle = new obstacle(i, (sizeCanvas.height - 203), this);
                obstacles.add(obstacle);
            }
        }

    }

    public void verefyIntersection(Graphics2D g2) {
        dino.createRectangleDino(g2);
        for (int i = 0; i < obstacles.size(); i++) {
            obstacle.paintObstacles(g2, i);
            obstacle.createRectangleObstacle(g2, i);
            if (loseLife(i) == true) { //metodo para saber  si perdio una vida.
                if (dino.getLifes() >1) {
//                    try {
//                        singLose = new Sound("src\\Sounds\\lose.mp3");
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (JavaLayerException ex) {
//                        Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    dino.setLifes(dino.getLifes() - 1);
                    nroObstacleJump = nroObstacleJump + 1;
                    GameBoard.getJlifes().setText("Vidas " + dino.getLifes());
                } else {
                    pauseTrack();
                    gameOver();
                }

            }
            if (obstacles.get(i).getPosx() <= dino.getPosx() && nroObstacleJump < i) {
                nroObstacleJump = i;
                GameBoard.setScorePlayer(GameBoard.getScorePlayer() + 2);
                GameBoard.getJscore().setText("Score: " + GameBoard.getScorePlayer());

            }

        }
    }

    public boolean intersectObstacleHead(Rectangle2D rectangle) {
        return dino.getRectHead().intersects(rectangle);
    }

    public boolean intersectObstacleFoot(Rectangle2D rectangle) {
        return dino.getRectFoot().intersects(rectangle);
    }

    public boolean loseLife(int i) {
        boolean state = intersectObstacleHead(obstacle.getRectObstacle());//retorna verdadero si intersecta la cabeza con el obstaculo.
        boolean state2 = intersectObstacleFoot(obstacle.getRectObstacle());//retorna verdadero si intersecta los pies con el obstaculo.

        if (state == true || state2 == true) {

            if (nroObstacle != i) {
                nroObstacle = i;
                return true;
            }

        }
        return false;
    }

    public ArrayList<obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void changeLevel() {
        //nivel 2
        if (GameBoard.getScorePlayer() >= 50 && GameBoard.getScorePlayer() <= 58) {
            changeLevelCode();
            GameBoard.setScorePlayer(GameBoard.getScorePlayer() + 20);
            GameBoard.getJscore().setText("Score: " + GameBoard.getScorePlayer());
        }
        //nivel 3
        if (GameBoard.getScorePlayer() >= 600 && GameBoard.getScorePlayer() <= 608) {
            changeLevelCode();
        }
        //nivel 4
        if (GameBoard.getScorePlayer() >= 900 && GameBoard.getScorePlayer() <= 908) {
            changeLevelCode();
        }
        //nivel 5
        if (GameBoard.getScorePlayer() >= 1200 && GameBoard.getScorePlayer() <= 1208) {
            changeLevelCode();
        }

    }

    public void changeLevelCode() {
        window.showIntro();
        
        try {
            singLose = new Sound("src\\Sounds\\changeL.mp3");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CanvasTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        speedTrack = speedTrack - 0.5;
        dino.setSpeedDino(dino.getSpeedDino() - 0.2);
        dino.setSpeedClone(dino.getSpeedClone() - 0.2);
        window.intro.setNivelPlayer(window.intro.getNivelPlayer() + 1);
        pauseTrack();
        //generateObstacles();
        //dino.stop();
    }

    public void gameOver() {

        if (JOptionPane.showConfirmDialog(null, "Puntaje: " + GameBoard.getScorePlayer() + "\n¿Desea jugar de nuevo?", "Game Over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
        //    reset();
            DinosaurGoogle.x.dispose();
            windowInitial x = new windowInitial("Dinosaurio de Google");
            x.setVisible(true);

        } else {
            System.exit(0);
        }

    }

    public void reset() {
        pauseTrack();
        window.intro.setNivelPlayer(1);
        dino.setLifes(3);
        GameBoard.setScorePlayer(0);
        GameBoard.getJlifes().setText("Vidas: " + dino.getLifes());
        GameBoard.getJscore().setText("Puntos: " + GameBoard.getScorePlayer());
        speedTrack = 5;
        dino.setSpeedDino(3);
        dino.setSpeedClone(3);
        window.showIntro();
        generateObstacles();
    }

}
