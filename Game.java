import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Dictionary dictionary;
    private Board board;
    private Parser parser;
    private LetterBag letterBag;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    public Game(){
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
        System.out.print("There are 4 different Commands."
        +"\n2 of them, \'help\' and \'pass\'. These both only require you to input those words alone."
        +"\nshuffle, however is more complicated. type \'shuffle (letter1) (letter2)\' to shuffle any number of letters in your hand."
        +"For example, with a hand of g d a e f p q you can type \"shuffle g d a\" which would shuffle the g d and a tiles back into the bag."
        +"\n However, the most complex command is place. place is split into: place <direction> (<x Coordinate> <yCoordinate> <letter>) (<x Coordinate> <yCoordinate> <letter>)"
        +"X coordinates are A to O, not case sensitive. Y coordinates are 1 to 9, or ONE to FIFTEEN. 10-15 does not work. letter is a letter in your hand."
        +"\n \n Sidenote: 10 to 15 does not work for the same reason that place is so clunky: the parser is \'going out to the family farm\' if you know what i mean when we add the GUI");
    }

    public static void main(String[] args){
        Game game = new Game();
        Parser parser = new Parser();
        game.processCommand(parser.getCommand());
    }
}