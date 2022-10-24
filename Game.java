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
            //place(command.getSecondWord(), command.getCoordinates(), command.getLetters());
        }

        
    }

    private void shuffleHand(String letters) {
    }

    private void place(String direction, Coordinates cord, String word) {

        //Coordinates tempCord = cord;
        //this will get rid of the brackets leaving the original word behind
        String temp = word.replaceAll("[^a-zA-Z0-9]","");
        //check if its a legal word
       // if(Dictionary.searchWord(temp)){

        //}

        for(int i = 0; i < temp.length(); i++) {
            //check if the tiles are free or not


            Coordinates tempCordX = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(cord.getXCoordinate().ordinal()+1)),cord.getYCoordinate());
            Coordinates tempCordY = new Coordinates(cord.getXCoordinate(),(Coordinates.yCoordinate.ordinalToYCoordinate(cord.getYCoordinate().ordinal()+1)));
            //this is only if we need to check right of the x axis
            if (Board.checkFree(tempCordX)) {
                //check that if the letter is already on the board
                if(temp.indexOf(i) == Board.getLetter(cord)){
                    //addLetter(temp.indexOf(i));
                }
            }
            //this will check down the y axis
            if(Board.checkFree(tempCordY)){
                if(temp.indexOf(i) == Board.getLetter(cord)){
                    //addLetter(temp.indexOf(i));
                }
            }


            }


        String possibleWord = "";
        Coordinates tempCord = cord;
        while(!Board.checkFree(tempCord)){
            //if we are checking right
            tempCord = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(cord.getXCoordinate().ordinal()+1)),cord.getYCoordinate());
            possibleWord+=Board.getLetter(tempCord);

        }
        while(!Board.checkFree(tempCord)){
            //if we are checking left
            tempCord = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(cord.getXCoordinate().ordinal()-1)),cord.getYCoordinate());
            possibleWord+=Board.getLetter(tempCord);

        }

        while(!Board.checkFree(tempCord)){
            //if we are checking up
            tempCord = new Coordinates(cord.getXCoordinate(),(Coordinates.yCoordinate.ordinalToYCoordinate(cord.getYCoordinate().ordinal()-1)));
            possibleWord+=Board.getLetter(tempCord);

        }
        while(!Board.checkFree(tempCord)){
            //if we are checking down
            tempCord = new Coordinates(cord.getXCoordinate(),(Coordinates.yCoordinate.ordinalToYCoordinate(cord.getYCoordinate().ordinal()+1)));
            possibleWord+=Board.getLetter(tempCord);

        }


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
        +"X coordinates are A to O, not case sensitive. Y coordinates ONE to FIFTEEN. Letter is a letter in your hand.");
    }

    private void legalWord(){


    }

    private void legalPlacing(Parser getCords, Parser getWord){

    }


    public static void main(String[] args){
        Game game = new Game();
        Parser parser = new Parser();
        game.processCommand(parser.getCommand());
    }
}