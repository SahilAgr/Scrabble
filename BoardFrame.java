import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class BoardFrame extends JFrame{//} implements TicTacToeView{

    public static final int BOARDLENGTH = 15;
    public static final int PLAYERTILES = 7;
    private JButton[][] buttons;
    private JLabel[] tileButtons;
    private Player currentPlayer;

    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};
    private char testlist[] = {'A','B','C','D','E','F','G'};

    public BoardFrame(){
        super("Scrabble");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(BOARDLENGTH+2,BOARDLENGTH+1));

        //scrabbleModel model = new scrabbleModel();
        currentPlayer = new Player("Dave");
        //model.addScrabbleView(this);

        buttons = new JButton[BOARDLENGTH][BOARDLENGTH];
        tileButtons = new JLabel[PLAYERTILES];

        ScrabbleController controller = new ScrabbleController();

        for (int i = 0; i < BOARDLENGTH+1; i++) {
            JLabel label = new JLabel(valueOf(rows[i]),SwingConstants.CENTER);
            this.add(label);
        }

        for (int i = 0; i < BOARDLENGTH; i++) {
            JLabel label = new JLabel(valueOf(i+1),SwingConstants.CENTER);
            this.add(label);
            for (int j = 0; j < BOARDLENGTH; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                int x = i;
                int y = j;
                b.addActionListener(controller);
                this.add(b);
            }
        }
//        for(Tile tile: Player.gethand()){
//            tileButtons[++] = tile;
//        }
        for (int i = 0; i<4; i++){
            JLabel l = new JLabel("");
            this.add(l);
        }
        JLabel currPlayerDisplay = new JLabel(currentPlayer.getName()+"\'s",SwingConstants.CENTER);
        JLabel turnDisplay = new JLabel("turn:", SwingConstants.CENTER);
        this.add(currPlayerDisplay);
        this.add(turnDisplay);
        for (int i = 0; i<PLAYERTILES; i++){
            JLabel l = new JLabel(valueOf(testlist[i]),SwingConstants.CENTER);
            tileButtons[i] = l;
            this.add(l);
        }




        setSize(690,706);
        this.setVisible(true);
    }
    public void errorMessage(){

    }

    public void update(ScrabbleEvent e) {
        //JOptionPane
        //buttons[e.getX()][e.getY()].setText(label);
    }

    public static void main(String[] args) {
        new BoardFrame();
    }
}
