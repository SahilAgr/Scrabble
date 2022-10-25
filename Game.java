import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Dictionary dictionary;
    private Board board;
    private Parser parser;
    private LetterBag letterBag;
    private int countdown;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    public Game(){
        this.parser = new Parser();
        this.board = new Board();
        this.letterBag = new LetterBag();
        this.dictionary = new Dictionary();
        this.players = new ArrayList<>();

        Scanner userInput = new Scanner(System.in);

        System.out.println("welcome to scrabble");

        System.out.println("how many players today?");
        int playerCount = userInput.nextInt();
        int countdown = playerCount - 1;
        for (int i = 0; i < playerCount; i++){
            players.add(new Player());

        }
        for(Player p:players){
            p.addLettersToHand(letterBag.getRandomLetters(7));
        }

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
        if(letterBag.isEmpty()){
            if(countdown != 0){
                countdown--;
            }
            else{
                return true;
            }
        }
        return false;
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

        //this will get rid of the brackets leaving the original word behind
        String temp = word.replaceAll("[^a-zA-Z0-9]","");
        //check if its a legal word

        if(Dictionary.isLegalWord(temp)){
            Coordinates tempCord = new Coordinates(cord.getXCoordinate(),cord.getYCoordinate());
            for(int i = 0; i < temp.length(); i++) {
                //check if the tiles are free or not

                //this is only if we need to check right of the x axis
                if (direction == "right") {
                    if(Board.checkFree(tempCord) && players.get(0).hasLetter(temp.charAt(i))){
                        Board.placeTile(tempCord,players.get(0).removeLetter(temp.charAt(i)));
                    }
                    else if(temp.charAt(i) != Board.getLetter(tempCord)){
                        break;
                    }else if(Board.checkFree(tempCord) && !players.get(0).hasLetter(temp.charAt(i))){
                        break;
                    }
                    upDownCheck(tempCord);
                    tempCord = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(cord.getXCoordinate().ordinal()+i)),cord.getYCoordinate());
                    //check that if the letter is already on the board



                }
                //this will check down the y axis
                if(direction == "down") {
                    if(Board.checkFree(tempCord) && players.get(0).hasLetter(temp.charAt(i))){
                        Board.placeTile(tempCord,players.get(0).removeLetter(temp.charAt(i)));
                    }
                    else if(temp.charAt(i) != Board.getLetter(tempCord)){
                        break;
                    }else if(Board.checkFree(tempCord) && !players.get(0).hasLetter(temp.charAt(i))){
                        break;
                    }
                    leftRightCheck(tempCord);
                    tempCord = new Coordinates(cord.getXCoordinate(), (Coordinates.yCoordinate.ordinalToYCoordinate(cord.getYCoordinate().ordinal() + i)));

                }
            }

        }

}

    public String leftRightCheck(Coordinates checkCord){
        String possibleWord = "";
        int direction = -1;
        while(!Board.checkFree(checkCord)) {
            //if we are checking left
            checkCord = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(checkCord.getXCoordinate().ordinal() + direction)), checkCord.getYCoordinate());
        }
        while(!Board.checkFree(checkCord)) {
            //if we are checking left
            checkCord = new Coordinates((Coordinates.xCoordinate.ordinalToXCoordinate(checkCord.getXCoordinate().ordinal() + direction*-1)), checkCord.getYCoordinate());
            possibleWord+=Board.getLetter(checkCord);
        }

        return possibleWord;



    }
    public String upDownCheck(Coordinates checkCord){
        String possibleWord = "";
        int direction = -1;
        while(!Board.checkFree(checkCord)) {
            //if we are checking up
            checkCord = new Coordinates(checkCord.getXCoordinate(),(Coordinates.yCoordinate.ordinalToYCoordinate(checkCord.getYCoordinate().ordinal()+direction)));
        }
        while(!Board.checkFree(checkCord)) {
            //if we are checking left
            checkCord = new Coordinates(checkCord.getXCoordinate(),(Coordinates.yCoordinate.ordinalToYCoordinate(checkCord.getYCoordinate().ordinal()+direction*-1)));
            possibleWord+=Board.getLetter(checkCord);
        }

        return possibleWord;



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