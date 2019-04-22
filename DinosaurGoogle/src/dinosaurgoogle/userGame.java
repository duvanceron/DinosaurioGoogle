package dinosaurgoogle;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class userGame extends JPanel {

    private JLabel Jlifes, Jscore;
    //private int quantityLifes ;
    private Box upBox;
    private int scorePlayer;

    public userGame() {
        this.setBackground(new Color(80, 114, 255));
        scorePlayer=0;
        initComponents();
    }

    public void initComponents() {
        upBox = Box.createHorizontalBox();
        upBox.setBorder(BorderFactory.createTitledBorder(null, "Play Ball", TitledBorder.LEFT, TitledBorder.TRAILING, new Font(Font.DIALOG_INPUT, Font.BOLD, 18), Color.WHITE));
        Jlifes = new JLabel("Vidas: 😃 😃 😃");
        Jlifes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        Jlifes.setForeground(Color.white);
        Jscore = new JLabel("Puntos: "+scorePlayer);
        Jscore.setForeground(Color.white);
        Jscore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        upBox.add(Box.createHorizontalStrut(10));//Crea un espacio entre dos componentes
        upBox.add(Jlifes);
        upBox.add(Box.createHorizontalStrut(10));//Crea un espacio entre dos componentes
        upBox.add(Jscore);

        this.add(upBox);
    }

    public JLabel getJlifes() {
        return Jlifes;
    }

    public void setJlifes(JLabel Jlifes) {
        this.Jlifes = Jlifes;
    }

    public JLabel getJscore() {
        return Jscore;
    }

    public void setJscore(JLabel Jscore) {
        this.Jscore = Jscore;
    }


    public Box getUpBox() {
        return upBox;
    }

    public void setUpBox(Box upBox) {
        this.upBox = upBox;
    }

    public int getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(int scorePlayer) {
        this.scorePlayer = scorePlayer;
    }
    
    

}
