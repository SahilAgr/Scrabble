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

        board = model.getBoard();

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
        /*
        buttons[1][1].setBackground(doubleWordColor);
        buttons[2][2].setBackground(doubleWordColor);
        buttons[3][3].setBackground(doubleWordColor);
        buttons[4][4].setBackground(doubleWordColor);
        buttons[10][10].setBackground(doubleWordColor);
        buttons[11][11].setBackground(doubleWordColor);
        buttons[12][12].setBackground(doubleWordColor);
        buttons[13][13].setBackground(doubleWordColor);
        buttons[1][13].setBackground(doubleWordColor);
        buttons[2][12].setBackground(doubleWordColor);
        buttons[3][11].setBackground(doubleWordColor);
        buttons[4][10].setBackground(doubleWordColor);
        buttons[10][4].setBackground(doubleWordColor);
        buttons[11][3].setBackground(doubleWordColor);
        buttons[12][2].setBackground(doubleWordColor);
        buttons[13][1].setBackground(doubleWordColor);
        buttons[1][1].setOpaque(true);
        buttons[2][2].setOpaque(true);
        buttons[3][3].setOpaque(true);
        buttons[4][4].setOpaque(true);
        buttons[10][10].setOpaque(true);
        buttons[11][11].setOpaque(true);
        buttons[12][12].setOpaque(true);
        buttons[13][13].setOpaque(true);
        buttons[1][13].setOpaque(true);
        buttons[2][12].setOpaque(true);
        buttons[3][11].setOpaque(true);
        buttons[4][10].setOpaque(true);
        buttons[10][4].setOpaque(true);
        buttons[11][3].setOpaque(true);
        buttons[12][2].setOpaque(true);
        buttons[13][1].setOpaque(true);
        Color tripleWordColor = new Color(249,106,79);
        buttons[0][7].setBackground(tripleWordColor);
        buttons[7][0].setBackground(tripleWordColor);
        buttons[14][0].setBackground(tripleWordColor);
        buttons[0][14].setBackground(tripleWordColor);
        buttons[14][7].setBackground(tripleWordColor);
        buttons[7][14].setBackground(tripleWordColor);
        buttons[14][14].setBackground(tripleWordColor);
        buttons[0][0].setBackground(tripleWordColor);
        buttons[0][7].setOpaque(true);
        buttons[7][0].setOpaque(true);
        buttons[14][0].setOpaque(true);
        buttons[0][14].setOpaque(true);
        buttons[14][7].setOpaque(true);
        buttons[7][14].setOpaque(true);
        buttons[14][14].setOpaque(true);
        buttons[0][0].setOpaque(true);
        Color doubleLetterColor = new Color(194,214,213);
        buttons[0][3].setBackground(doubleLetterColor);
        buttons[0][11].setBackground(doubleLetterColor);
        buttons[3][0].setBackground(doubleLetterColor);
        buttons[11][0].setBackground(doubleLetterColor);
        buttons[3][7].setBackground(doubleLetterColor);
        buttons[7][3].setBackground(doubleLetterColor);
        buttons[2][8].setBackground(doubleLetterColor);
        buttons[8][2].setBackground(doubleLetterColor);
        buttons[2][6].setBackground(doubleLetterColor);
        buttons[3][14].setBackground(doubleLetterColor);
        buttons[14][3].setBackground(doubleLetterColor);
        buttons[6][6].setBackground(doubleLetterColor);
        buttons[6][8].setBackground(doubleLetterColor);
        buttons[8][6].setBackground(doubleLetterColor);
        buttons[8][8].setBackground(doubleLetterColor);
        buttons[6][2].setBackground(doubleLetterColor);
        buttons[14][11].setBackground(doubleLetterColor);
        buttons[11][14].setBackground(doubleLetterColor);
        buttons[11][7].setBackground(doubleLetterColor);
        buttons[7][11].setBackground(doubleLetterColor);
        buttons[12][6].setBackground(doubleLetterColor);
        buttons[6][12].setBackground(doubleLetterColor);
        buttons[12][8].setBackground(doubleLetterColor);
        buttons[8][12].setBackground(doubleLetterColor);
        buttons[0][3].setOpaque(true);
        buttons[0][11].setOpaque(true);
        buttons[3][0].setOpaque(true);
        buttons[11][0].setOpaque(true);
        buttons[3][7].setOpaque(true);
        buttons[7][3].setOpaque(true);
        buttons[2][8].setOpaque(true);
        buttons[8][2].setOpaque(true);
        buttons[2][6].setOpaque(true);
        buttons[3][14].setOpaque(true);
        buttons[14][3].setOpaque(true);
        buttons[6][6].setOpaque(true);
        buttons[6][8].setOpaque(true);
        buttons[8][6].setOpaque(true);
        buttons[8][8].setOpaque(true);
        buttons[6][2].setOpaque(true);
        buttons[14][11].setOpaque(true);
        buttons[11][14].setOpaque(true);
        buttons[11][7].setOpaque(true);
        buttons[7][11].setOpaque(true);
        buttons[12][6].setOpaque(true);
        buttons[6][12].setOpaque(true);
        buttons[12][8].setOpaque(true);
        buttons[8][12].setOpaque(true);
        Color tripleLetterColor = new Color(65,159,184);
        buttons[1][5].setBackground(tripleLetterColor);
        buttons[5][1].setBackground(tripleLetterColor);
        buttons[1][9].setBackground(tripleLetterColor);
        buttons[9][1].setBackground(tripleLetterColor);
        buttons[5][5].setBackground(tripleLetterColor);
        buttons[5][9].setBackground(tripleLetterColor);
        buttons[9][5].setBackground(tripleLetterColor);
        buttons[9][9].setBackground(tripleLetterColor);
        buttons[13][5].setBackground(tripleLetterColor);
        buttons[5][13].setBackground(tripleLetterColor);
        buttons[13][9].setBackground(tripleLetterColor);
        buttons[9][13].setBackground(tripleLetterColor);
        buttons[1][5].setOpaque(true);
        buttons[5][1].setOpaque(true);
        buttons[1][9].setOpaque(true);
        buttons[9][1].setOpaque(true);
        buttons[5][5].setOpaque(true);
        buttons[5][9].setOpaque(true);
        buttons[9][5].setOpaque(true);
        buttons[9][9].setOpaque(true);
        buttons[13][5].setOpaque(true);
        buttons[5][13].setOpaque(true);
        buttons[13][9].setOpaque(true);
        buttons[9][13].setOpaque(true);

         */

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
    public void returnAIMessage(Placement place){
        JOptionPane.showMessageDialog(null, "AI placed: " + place.getErrorMessage() + "for "
                + place.getScore()+ " points\nTotal points: " + currentPlayer.getScore());
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
        currentPlayer = e.getPlayer();

        Placement place = e.getPlace();
        board = e.getBoard();
        Color tileColor = new Color(247, 243, 237);

        board.printBoard();
        //The row (ABC)

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
        //if (currentPlayer instanceof AIPlayer) {
        //    returnAIMessage(place);
        //} else {
            returnMessage(place);
        //}
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

