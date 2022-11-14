import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class BoardFrame extends JFrame implements ScrabbleView, ActionListener {

    public static final int BOARDLENGTH = 15;
    public static final int PLAYERTILES = 7;
    private Board board;
    private JButton[][] buttons;
    private JLabel[] tileButtons;
    private Player currentPlayer;
    private Game model;
    private JLabel Score;
    private JLabel name;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2, m3;

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

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem("Shuffle");
        m2 = new JMenuItem("Pass");
        m3 = new JMenuItem("Help");
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        currentPlayer = model.getCurrPlayer();

        buttons = new JButton[BOARDLENGTH][BOARDLENGTH];
        Color blankColor = new Color(233,224,206);
        tileButtons = new JLabel[PLAYERTILES];

        for (int i = 0; i < BOARDLENGTH+1; i++) {
            JLabel label = new JLabel(valueOf(rows[i]),SwingConstants.CENTER);
            this.add(label);
        }

        for (int i = 0; i < BOARDLENGTH; i++) {
            JLabel label = new JLabel(valueOf(i+1),SwingConstants.CENTER);
            this.add(label);
            for (int j = 0; j < BOARDLENGTH; j++) {
                ScrabbleController controller = new ScrabbleController(model,new Coordinates(j,i));
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                b.setBackground(blankColor);
                b.addActionListener(controller);
                this.add(b);
            }
        }
        for (int i = 0; i<4; i++){
            JLabel l = new JLabel("");
            this.add(l);
        }

        name = new JLabel(currentPlayer.getName()+"\'s",SwingConstants.CENTER);
        JLabel turnDisplay = new JLabel("turn:", SwingConstants.CENTER);
        this.add(name);
        this.add(turnDisplay);
        for (int i = 0; i<PLAYERTILES; i++){
            JLabel l = new JLabel(valueOf(currentPlayer.getHand().get(i).getString()),SwingConstants.CENTER);
            tileButtons[i] = l;
            this.add(l);
        }

        Color c1 = new Color(255,102,102);
        buttons[7][7].setBackground(c1);
        buttons[7][7].setOpaque(true);

        JLabel blank = new JLabel("");
        this.add(blank);
        JLabel score = new JLabel("Score:", SwingConstants.CENTER);
        this.add(score);
        Score = new JLabel(valueOf(currentPlayer.getScore()), SwingConstants.CENTER);
        this.add(Score);

        //set larger
        setSize(750,800);
        this.setVisible(true);
    }

    public void returnMessage(Placement place){
        if (place.isLegalPlace()){
            JOptionPane.showMessageDialog(null, place.getErrorMessage()+ place.getScore()+ " points");
        } else {
            JOptionPane.showMessageDialog(null, place.getErrorMessage());
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        switch(s){
            case "Shuffle": String letters = JOptionPane.showInputDialog(null, "What letters would you like to shuffle? (Leave blank for full shuffle):");
            model.shuffleHand(letters);
                break;
            case "Pass": model.passTurn();
                break;
            case "Help": help();
                break;
        }

    }

    public void help(){
        JOptionPane.showMessageDialog(null, "How to Play: Look at the board and select a square " +
                "to which you would like place \na legal word that can be generated using your tiles (Displayed at the" +
                " bottom). \nFor further in-depths rules see: https://scrabble.hasbro.com/en-us/rules");
    }

    public void update(GameEvent e) {
        currentPlayer= e.getPlayer();
        Placement place = e.getPlace();
        board = e.getBoard();
        Color tileColor = new Color(247, 243, 237);

        board.printBoard();
        //The row (ABC)

        for (int i = 0; i < BOARDLENGTH; i++) {
            for (int j = 0; j < BOARDLENGTH; j++) {
                if(!board.getLetter(new Coordinates(j,i)).equals(".")) {
                    buttons[i][j].setText(board.getLetter(new Coordinates(j, i)));
                    buttons[i][j].setBackground(tileColor);
                }
            }
        }
        name.setText(currentPlayer.getName()+"\'s");
        Score.setText(valueOf(currentPlayer.getScore()));

        for (int i = 0; i<currentPlayer.getHand().size()-1; i++){
            tileButtons[i].setText(currentPlayer.getHand().get(i).getString());
        }
        for (int j =0; j<PLAYERTILES-currentPlayer.getHand().size()-1; j++){
            tileButtons[j+currentPlayer.getHand().size()].setText("");
        }

        returnMessage(place);
    }
    @Override
    public void gameOver(ArrayList<Player> players) {
        String message = "The game has finished. Scores are as follows:\n" ;
        for(Player p: players){
            message += "Player " + p.getName() + " Scored " + p.getScore() +",\n";
        }
        JOptionPane.showMessageDialog(null, message , "Game Over", ABORT);
        this.dispose();
        this.setVisible(false);
        
    }

    public static void main(String[] args) {
        new BoardFrame();
    }
}

