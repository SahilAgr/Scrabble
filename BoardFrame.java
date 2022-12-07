import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.Serializable;


import static java.lang.String.valueOf;

public class BoardFrame extends JFrame implements ScrabbleView, ActionListener, Serializable {

    public static final long serialVersionUID = 1L;
    public static final int BOARDLENGTH = 15;
    public static final int PLAYERTILES = 7;

    public static final Color BLANKCOLOR = new Color(233,224,206);
    private Board board;
    private static JButton[][] buttons;

    public static JButton[][] customButtons;
    private JLabel[] tileButtons;
    private Player currentPlayer;
    private Game model;
    private JLabel Score;
    private JLabel name;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2, m3, m4, m5, m6;

    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    public BoardFrame(Game game, JButton[][] buttonArray){
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

        if(game == null) {
            model = new Game(players);
        } else {
            model = game;
        }

        model.addScrabbleView(this);

        board = model.getBoard();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem("Shuffle");
        m2 = new JMenuItem("Pass");
        m3 = new JMenuItem("Help");
        m4 = new JMenuItem("Save");
        m5 = new JMenuItem("Load");
        m6 = new JMenuItem("Create Custom Board");

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);

        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        menu.add(m5);
        menu.add(m6);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        currentPlayer = model.getCurrPlayer();
        if (buttonArray == null){
            System.out.println("here1");
            buttons = new JButton[BOARDLENGTH][BOARDLENGTH];
        } else {
            System.out.println("here2");
            buttons = buttonArray;
        }
        customButtons = new JButton[BOARDLENGTH][BOARDLENGTH];
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
                b.setBackground(BLANKCOLOR);
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


        ImageIcon iconA = new ImageIcon("BlackStar.png");
        buttons[7][7].setBackground(Tile.w2);
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

            case "Save":

                try {
                    //DataStorage.save(this.model, "SavedFile.save");
                    JOptionPane.showMessageDialog(null, "Game is saved");
                } catch (Exception ex) {
                    System.out.println("Couldn't save: " + ex.getMessage());
                }
                break;

            case "Load":
                try {
                    Game loadedModel = (Game) DataStorage.load("SavedFile.save");
                    new BoardFrame(loadedModel,null);
                    JOptionPane.showMessageDialog(null, "Game is loaded (Test 'LOAD' word anywhere in the board down/right to load the game)");

                } catch (Exception ex) {
                    System.out.println("Couldn't load save data: " + ex.getMessage());
                }

                break;
            case "Create Custom Board":
                new CustomBoardFrame();
                this.dispose();
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
        //if (currentPlayer instanceof AIPlayer) {
        //    returnAIMessage(place);
        //} else {
        returnMessage(place);
        //}
    }

    public static void updateCustom(){
        for (int i = 0; i < BOARDLENGTH; i++) {
            for (int j = 0; j < BOARDLENGTH; j++) {
                buttons[i][j].setBackground(customButtons[i][j].getBackground());
            }
        }
        new BoardFrame(null, BoardFrame.buttons);
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
        new BoardFrame(null, null);
    }
}
