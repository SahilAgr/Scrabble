import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class BoardFrame extends JFrame implements ScrabbleView, ActionListener {

    public static final int BOARDLENGTH = 15;
    public static final int PLAYERTILES = 7;
    public static final String SHUFFLE = "Shuffle";
    public static final String PASS = "Pass";
    public static final String HELP = "Help";
    public static final String LOAD = "Load";
    public static final String SAVE = "Save";

    private Board board;
    private JButton[][] buttons;
    private JLabel[] tileButtons;
    private Player currentPlayer;
    private Game model;
    private JLabel Score;
    private JLabel name;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2, m3, m4, m5;

    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    public BoardFrame(){
        super("Scrabble");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(BOARDLENGTH+2,BOARDLENGTH+1));

        int numPlayers = 0;
        int numAI = -4;

            while ((numPlayers < 1) ||  (numPlayers > 4)) {
                try{
                    numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players? (1-4)"));
                }catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
                    nfe.printStackTrace();
                }
            }



        while ((numAI < 0) ||  (numAI > 4-numPlayers )) {
            try{
            numAI = Integer.parseInt(JOptionPane.showInputDialog("How many AI player(s)? (Up to "+
                    (4-numPlayers) +" AI players)"));
            }catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
                nfe.printStackTrace();
            }
        }

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++){
            String playerName = JOptionPane.showInputDialog("Please enter P"+(i+1)+"'s name:");
            players.add(new Player(playerName));
            System.out.println(playerName);
        }

        for (int i = 0; i < numAI; i++){
            String playerName = JOptionPane.showInputDialog("Please enter AI "+(i+1)+"'s name:");
            players.add(new AIPlayer(playerName));
            System.out.println(playerName);
        }


        model = new Game(players);

        model.addScrabbleView(this);

        board = model.getBoard();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem(SHUFFLE);
        m2 = new JMenuItem(PASS);
        m3 = new JMenuItem(HELP);
        m4 = new JMenuItem(SAVE);
        m5 = new JMenuItem(LOAD);
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        menu.add(m5);

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

        Color doubleWordColor = new Color(249,188,166);
        ImageIcon iconA = new ImageIcon("BlackStar.png");
        buttons[7][7].setBackground(doubleWordColor);
        buttons[7][7].setIcon(iconA);
        buttons[7][7].setText(null);
        buttons[7][7].setOpaque(true);

        for (int i = 0; i < BOARDLENGTH; i++) {
            for (int j = 0; j < BOARDLENGTH; j++) {
                buttons[i][j].setBackground(board.getGameBoard()[i][j].getColour());
                buttons[i][j].setOpaque(true);
            }
        }

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
            case SHUFFLE: String letters = JOptionPane.showInputDialog(null, "What letters would you like to shuffle? (Leave blank for full shuffle):");
            model.shuffleHand(letters);
                break;
            case PASS: model.passTurn();
                break;
            case HELP: help();
                break;
            case SAVE: String fileNameS = JOptionPane.showInputDialog(null, "Please enter the filename of the file you wish to save to:");
                //model.save(fileNameS)
                break;
            case LOAD: String fileNameL = JOptionPane.showInputDialog(null, "Please enter the filename of the file you wish to load from:");
                //model.Load(fileNameL)
                break;
        }

    }
    public void returnAIMessage(Placement place){
        JOptionPane.showMessageDialog(null, "AI placed: " + place.getErrorMessage() + "for "
                + place.getScore()+ " points\nTotal points: " + currentPlayer.getScore());
    }

    public void help(){
        JOptionPane.showMessageDialog(null, "How to Play: Look at the board and select a square " +
                "to which you would like place \na legal word that can be generated using your tiles (Displayed at the" +
                " bottom). \nFor further in-depths rules see: https://scrabble.hasbro.com/en-us/rules");
    }

    public void update(GameEvent e) {
        currentPlayer = e.getPlayer();

        Placement place = e.getPlace();
        board = e.getBoard();
        Color tileColor = new Color(247, 243, 237);

        board.printBoard();
        //The row (ABC)
        if(!board.getLetter(new Coordinates(7,7)).equals("+")){
            buttons[7][7].setIcon(null);
        }

        for (int i = 0; i < BOARDLENGTH; i++) {
            for (int j = 0; j < BOARDLENGTH; j++) {
                String s = board.getLetter(new Coordinates(j,i));
                if(!(s.equals(".") || s.equals("3W") || s.equals("2W") || s.equals("3L") || s.equals("2L") || s.equals("+"))) {
                    buttons[i][j].setText(board.getLetter(new Coordinates(j, i)));
                    buttons[i][j].setBackground(tileColor);
                }
            }
        }
        name.setText(currentPlayer.getName()+"\'s");
        Score.setText(valueOf(currentPlayer.getScore()));

        for (int i = 0; i<PLAYERTILES; i++){
            tileButtons[i].setText(currentPlayer.getHand().get(i).getString());
        }


        if (currentPlayer instanceof AIPlayer) {
            returnAIMessage(place);
        } else {
            returnMessage(place);
        }

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
