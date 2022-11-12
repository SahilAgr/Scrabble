import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class BoardFrame extends JFrame implements ScrabbleView{

    public static final int BOARDLENGTH = 15;
    public static final int PLAYERTILES = 7;
    private Board board;
    private JButton[][] buttons;
    private JLabel[] tileButtons;
    private Player currentPlayer;
    private Game model;

    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    public BoardFrame(){
        super("Scrabble");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(BOARDLENGTH+2,BOARDLENGTH+1));

        int numPlayers = 0;
        while ((numPlayers < 1) ||  (numPlayers > 4)) {
            numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players? (1-4)"));
        }
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++){
            String playerName = JOptionPane.showInputDialog("Please enter P"+(i+1)+"'s name:");
            players.add(new Player(playerName));
            System.out.println(playerName);
        }



        model = new Game(players);

        model.addScrabbleView(this);


        currentPlayer = model.getCurrPlayer();

        buttons = new JButton[BOARDLENGTH][BOARDLENGTH];
        tileButtons = new JLabel[PLAYERTILES];

        for (int i = 0; i < BOARDLENGTH+1; i++) {
            JLabel label = new JLabel(valueOf(rows[i]),SwingConstants.CENTER);
            this.add(label);
        }

        for (int i = 0; i < BOARDLENGTH; i++) {
            JLabel label = new JLabel(valueOf(i+1),SwingConstants.CENTER);
            this.add(label);
            for (int j = 0; j < BOARDLENGTH; j++) {
                ScrabbleController controller = new ScrabbleController(model,new Coordinates(i,j));
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                b.addActionListener(controller);
                this.add(b);
            }
        }
        for (int i = 0; i<4; i++){
            JLabel l = new JLabel("");
            this.add(l);
        }
        JLabel currPlayerDisplay = new JLabel(currentPlayer.getName()+"\'s",SwingConstants.CENTER);
        JLabel turnDisplay = new JLabel("turn:", SwingConstants.CENTER);
        this.add(currPlayerDisplay);
        this.add(turnDisplay);
        for (int i = 0; i<PLAYERTILES; i++){
            JLabel l = new JLabel(valueOf(currentPlayer.getHand().get(i).getString()),SwingConstants.CENTER);
            tileButtons[i] = l;
            this.add(l);
        }


        setSize(690,706);
        this.setVisible(true);
    }

    public void errorMessage(){
        JOptionPane.showMessageDialog(null, "You cannot place this word here!");
    }

    public void update(GameEvent e) {
        currentPlayer= e.getPlayer();
        Placement place = e.getPlace();
        board = e.getBoard();
        System.out.println("hi");
        this.removeAll();
        this.revalidate();
        this.repaint();
        board.printBoard();
        //The row (ABC)
        for (int i = 0; i < BOARDLENGTH+1; i++) {
            JLabel label = new JLabel(valueOf(rows[i]),SwingConstants.CENTER);
            this.add(label);
        }

        for (int i = 0; i < BOARDLENGTH; i++) {
            JLabel label = new JLabel(valueOf(i+1),SwingConstants.CENTER);
            this.add(label);
            for (int j = 0; j < BOARDLENGTH; j++) {
                ScrabbleController controller = new ScrabbleController(model,new Coordinates(i,j));
                if (board.getLetter(new Coordinates(i,j)).equals(".")) {
                    JButton b = new JButton("");
                    buttons[i][j] = b;
                    b.addActionListener(controller);
                    this.add(b);
                }else{
                    JButton b = new JButton(board.getLetter(new Coordinates(i,j)));
                    buttons[i][j] = b;
                    b.addActionListener(controller);
                    this.add(b);
                }


            }
        }
        for (int i = 0; i<4; i++){
            JLabel l = new JLabel("");
            this.add(l);
        }
        JLabel currPlayerDisplay = new JLabel(currentPlayer.getName()+"\'s",SwingConstants.CENTER);
        JLabel turnDisplay = new JLabel("turn:", SwingConstants.CENTER);
        this.add(currPlayerDisplay);
        this.add(turnDisplay);
        for (int i = 0; i<PLAYERTILES; i++){
            JLabel l = new JLabel(valueOf(currentPlayer.getHand().get(i)),SwingConstants.CENTER);
            tileButtons[i] = l;
            this.add(l);
        }

    }

    public static void main(String[] args) {
        new BoardFrame();
    }
}
