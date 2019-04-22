package dinosaurgoogle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;



public class southInitialL extends JPanel implements MouseListener, ActionListener {

    topInitialL topInSouth;
    centerInitialL centerInSouth;
    windowInitial windowInSouth;
    private GridBagConstraints key;
    private JButton playGame;
    private JButton scores, exit;

    public southInitialL() {
        key = new GridBagConstraints();
        setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        initComponents();

    }

    public final void addComponents(JPanel panel, Component component, int x, int y, int width, int height) {
        key.gridx = x;
        key.gridy = y;
        key.gridwidth = width;
        key.gridheight = height;
        key.fill = GridBagConstraints.CENTER;
        key.insets = new Insets(10, 10, 10, 10);
        panel.add(component, key);

    }

    public final void initComponents() {
        playGame = new JButton("PLAY GAME");
        playGame.setBackground(Color.BLACK);
        playGame.setForeground(Color.WHITE);
        playGame.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));
        playGame.setBorder(null);
        playGame.addMouseListener(this);
        playGame.addActionListener(this);
        scores = new JButton("SCORES");
        scores.setForeground(Color.WHITE);
        scores.setBackground(Color.BLACK);
        scores.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));
        scores.setBorder(null);
        scores.addMouseListener(this);
        exit = new JButton("SALIR");
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));
        exit.setBorder(null);
        exit.addMouseListener(this);

        addComponents(this, playGame, 5, 3, 5, 2);
        addComponents(this, scores, 5, 9, 5, 2);
        addComponents(this, exit, 5, 14, 5, 2);
        

    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == playGame) {
            playGame.setBackground(new Color(255, 64, 76));
            playGame.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 33));

        }
        if (me.getSource() == scores) {
            scores.setBackground(new Color(240, 96, 96));
            scores.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 33));
        }
        if (me.getSource() == exit) {
            exit.setBackground(new Color(201, 40, 62));
            exit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 33));
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == playGame) {
            playGame.setBackground(Color.BLACK);
            playGame.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));

        }
        if (me.getSource() == scores) {
            scores.setBackground(Color.BLACK);
            scores.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));
        }
        if (me.getSource() == exit) {
            exit.setBackground(Color.BLACK);
            exit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 28));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playGame) {
           windowInSouth.showIntro();
           
         //   windowInSouth.showCanvasTrack();
        }
    }

    public JButton getPlayGame() {
        return playGame;
    }

    public void setPlayGame(JButton playGame) {
        this.playGame = playGame;
    }

    public JButton getScores() {
        return scores;
    }

    public void setScores(JButton scores) {
        this.scores = scores;
    }

}
