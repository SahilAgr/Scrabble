import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;


import static java.lang.String.valueOf;

public class BoardFrame extends JFrame implements ScrabbleView, ActionListener {

    public static final long serialVersionUID = 1L;
    public static final int BOARDLENGTH = 15;
    private static final int PLAYERTILES = 7;
    public static final Color BLANKCOLOR = new Color(233,224,206);
    private static final String SHUFFLE = "Shuffle";
    private static final String PASS = "Pass";
    private static final String HELP = "Help";
    private static final String LOAD = "Load";
    private static final String SAVE = "Save";
    private static final String CUSTOM = "Create Custom Board";
    private static final String PRESET = "Choose Preset Board";
    private static final String NEWGAME = "New Game";


    private HashMap<String, String> boardTypes;
    private Board board;
    private JButton[][] buttons;

    public static Board customBoard;
    private JLabel[] tileButtons;
    private Player currentPlayer;
    private Game model;
    public static boolean skipLoad;
    private JLabel Score;
    private JLabel name;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2, m3, m4, m5, m6, m7, m8;

    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    public BoardFrame(){
        super("Scrabble");

        boardTypes = new HashMap<>();

        boardTypes.put("Standard", "./StandardBoard.json");
        boardTypes.put("Modern Art", "./InverseStandard.json");
        boardTypes.put("High Scoring", "./CrazyBoard.json");

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem(SHUFFLE);
        m2 = new JMenuItem(PASS);
        m3 = new JMenuItem(HELP);
        m4 = new JMenuItem(SAVE);
        m5 = new JMenuItem(LOAD);
        m6 = new JMenuItem(CUSTOM);
        m7 = new JMenuItem(PRESET);
        m8 = new JMenuItem(NEWGAME);
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m8.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        menu.add(m5);
        menu.add(m6);
        menu.add(m7);
        menu.add(m8);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(BOARDLENGTH+2,BOARDLENGTH+1));

        int testPlace = 0;
        if(!skipLoad) {
            String[] loadOptions = {"New Game", "Load Game"};
            testPlace = JOptionPane.showOptionDialog(null, "Would you start a new game or load from a previous game?",
                    "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, loadOptions, loadOptions[1]);
        }
        if (testPlace==1){
            /*loadoldgame();
            board = ;
            buttons = ;
            tileButtons = ;
            currentPlayer = ;
            model = ;
            Score = ;
            name = ;
            menuBar = ;
            private JMenu menu;
            private JMenuItem m1, m2, m3, m4, m5;

             */
        } else {
            if (!skipLoad) {
                String[] loadOptions1 = {"Standard", "Preset Custom"};
                String[] loadOptions2 = {"Modern Art", "Crazy Points"};
                String boardType;
                int isBoardStandard = 0;
                isBoardStandard = JOptionPane.showOptionDialog(null, "Would you like a standard board, or a Preset Custom Board?", "Select an option",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, loadOptions1, loadOptions1[1]);
                if (isBoardStandard == 0) {
                    boardType = "./StandardBoard.json";
                } else{
                    isBoardStandard = JOptionPane.showOptionDialog(null, "Which custom board would you like to use?", "Select an option",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, loadOptions2, loadOptions2[1]);
                    if(isBoardStandard == 0){
                        boardType = "./InverseStandard.json";
                    } else { boardType = "./CrazyBoard.json";}
                }

                customBoard = new Board(boardType);
            }

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

            model = new Game(players, customBoard);

            model.addScrabbleView(this);

            board = model.getBoard();

            currentPlayer = model.getCurrPlayer();

            buttons = new JButton[BOARDLENGTH][BOARDLENGTH];
            //Color blankColor = new Color(233,224,206);
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


    }



    public void returnMessage(Placement place){
        if (place.isLegalPlace()){
            JOptionPane.showMessageDialog(null, currentPlayer.getName() +" placed: " + place.getErrorMessage() + " for "
                    + place.getScore()+ " points\nTotal points: " + currentPlayer.getScore());
        } else {
            JOptionPane.showMessageDialog(null, place.getErrorMessage());
        }
    }
    public void returnAIMessage(Placement place){
        JOptionPane.showMessageDialog(null, "AI placed: " + place.getErrorMessage() + " for "
                + place.getScore()+ " points\nTotal points: " + currentPlayer.getScore());
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
                try {
                    //DataStorage.save(this.model, "SavedFile.save");
                    JOptionPane.showMessageDialog(null, "Game is saved");
                } catch (Exception ex) {
                    System.out.println("Couldn't save: " + ex.getMessage());
                }
                break;
            case LOAD: try {
                Game loadedModel = (Game) DataStorage.load("SavedFile.save");
                new BoardFrame();
                JOptionPane.showMessageDialog(null, "Game is loaded (Test 'LOAD' word anywhere in the board down/right to load the game)");

            } catch (Exception ex) {
                System.out.println("Couldn't load save data: " + ex.getMessage());
            }
                break;
            case CUSTOM:
                skipLoad = true;
                new CustomBoardFrame();
            this.dispose();
                break;
            case PRESET:
                skipLoad = true;
                //MATHEW CODE HERE
                break;
            case NEWGAME:
                skipLoad = false;
                new BoardFrame();
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
                if(! board.checkFree(new Coordinates(j,i))){
                    buttons[i][j].setText(board.getLetter(new Coordinates(j, i)));
                    buttons[i][j].setBackground(tileColor);
                }
            }
        }
        if (currentPlayer instanceof AIPlayer) {
            returnAIMessage(place);
        } else {
            returnMessage(place);
        }

        currentPlayer = e.getPlayer();
        if (currentPlayer instanceof AIPlayer){
            JOptionPane.showMessageDialog(this, "The AI is now placing. This WILL take a while, " +
                    "as the AI is now about to RAW SEARCH a 280k word dictionary about 100 times. Hopefully your pc doesn't burn!");
        }
        name.setText(currentPlayer.getName()+"\'s");
        Score.setText(valueOf(currentPlayer.getScore()));

        for (int i = 0; i<PLAYERTILES; i++){
            tileButtons[i].setText(currentPlayer.getHand().get(i).getString());
        }
    }

    public static void updateCustom() {
        new BoardFrame();
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
