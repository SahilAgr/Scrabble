import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Dictionary dictionary;
    private Board board;
    private Parser parser;
    private Map<letter,Integer> letterScores;
    private LetterBag letterBag;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    public Game(){
        createLetterScores();
        this.parser = new Parser();
        this.board = new Board();
        this.letterBag = new LetterBag();
        this.dictionary = new Dictionary();
        this.players = new ArrayList<>();

    
        // print welcome
        // ask for # of players
        // ask for names of players, make them
        // do all the hand stuff, you know how it is

    }

    public void turnOrder(){
        boolean gameOver = false;

        while (! gameOver){
            Command command = parser.getCommand();
            processCommand(command);
            gameOver = progressChecker();
        }
    }

    //checks how many tiles are left in the letterbag, then begins a countdown equal to the number of players. when that reaches zero, the game is over.
    public boolean progressChecker(){
        return true;
    }


    public void processCommand(Command command){
        if(command.invalidCommand()){
            System.out.println("invalid command");
            //print help message here?
        }
        else if(command.commandWord.equals("help")){
            printHelp();
        }
        else if (command.commandWord.equals("pass")){
            passPlayers();
        }
        else if (command.commandWord.equals("shuffle")){
            shuffleHand(command.getLetters());
        }
        else if (command.commandWord.equals("place")){
            place(command.getSecondWord(), command.getCoordinates(), command.getLetters());
        }

        
    }

    private void shuffleHand(ArrayList<String> letters) {
    }

    private void place(String secondWord, ArrayList<Coordinates> coordinates, ArrayList<String> letters) {

    }

    private void passPlayers() {
        //probably should have some way to autoprint next player's letters and score and boardstate.
    }

    private void printHelp() {
        System.out.print("get gud noob");
    }
}