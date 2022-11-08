import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The Game class that will drive the whole game including creating the board and the players
 * @authors  Matthew Huitema, Sahil Agrawal, Patrick Ma
 */

public class Game {
    private List<Player> players;
    private Player currPlayer;
    private Dictionary dictionary;
    private Board board;
    private Board tempBoard;
    private Parser parser;
    private LetterBag letterBag;
    private int countdown;
    private boolean isTurnOne;
    private boolean isTouching;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    /**
     * The constructor for the game that will create new players, the borad and all the letters
     */
    public Game(){
        this.parser = new Parser();
        this.board = new Board();
        this.letterBag = new LetterBag();
        this.dictionary = new Dictionary();
        this.players = new ArrayList<>();

        Scanner userInput = new Scanner(System.in);

        //gameView.jOptionPane("Welcome to Scrabble");
        System.out.println("welcome to scrabble");

        //gameView.jOptionPane("start replacing souts with gameView.joptionpane etc, once thats made");
        System.out.println("how many players today?");
        int playerCount = userInput.nextInt();
        while (  (playerCount < 1) ||  (playerCount > 4)){
            System.out.println("Between 1 and 4 players must participate.");
            playerCount = userInput.nextInt();
        }
        countdown = playerCount - 1;
        isTurnOne = true;
        for (int i = 0; i < playerCount; i++){
            players.add(new Player("Player" + (i+1)));

        }
        for(Player p:players){
            p.addLettersToHand(letterBag.getRandomLetters(7));
        }
        turnOrder();
        userInput.close();

    }

    /**
     * The turn order for the players that will also display player information
     */
    private void turnOrder(){
        boolean gameOver = false;
        
        while (! gameOver){
            for(Player p: players){
                currPlayer = p;
                    board.printBoard();
                    System.out.println("It is now "+ currPlayer.getName() +"'s turn.");
                    System.out.println("Their score is: " + currPlayer.getScore());
                    System.out.println("Their letters are: ");
                    for(Tile t: currPlayer.getHand()){
                        System.out.print(t.getLetter() +" ");
                    }
                    System.out.println("");  
                Command command;
                do{
                    System.out.println("Please type in a command.");
                    command = parser.getCommand();
                    gameOver = progressChecker();
                }
                while(! processCommand(command));
            }
        }
        System.out.println("game is over.");
        for(Player p: players){
            System.out.println(p.getName() + " scored :" +p.getScore());
        }
    }

    /**
     * checks how many tiles are left in the letterbag, then begins a countdown equal to the number of players. when that reaches zero, the game is over.
     * @return boolean
     */

    private boolean progressChecker(){
        if(letterBag.isEmpty()){
            if(countdown != 0){
                countdown--;
                System.out.println("This is your Last Turn!");
            }
            else{
                return true;
            }
        }
        return false;
    }


    /**
     * Process's the users commands
     * @param command Command
     * @return boolean
     */
    private boolean processCommand(Command command){
        if(command.invalidCommand()){
            System.out.println("invalid command");
            //print help message here?
        }
        else if(command.getCommandWord().equals("help")){
            printHelp();
            return false;
        }
        else if (command.getCommandWord().equals("pass")){
            return true;
        }
        else if (command.getCommandWord().equals("shuffle")){
            shuffleHand(command.getLetters());
            return true;
        }
        else if (command.getCommandWord().equals("place")){
            if( ! command.getSecondWord().equals("down") && ! command.getSecondWord().equals("right")){
                System.out.println(command.getSecondWord());
                System.out.println("Unrecognized Direction.");
                return false;
            }
            if(command.getCoordinates().getXCoordinate() == null || command.getCoordinates().getYCoordinate() == null){
                System.out.println("Unrecognized Coordinate.");
                return false;
            }
            if(command.getLetters().length() == 0){
                System.out.println("Either no word, no coordinate, or no direction.");
                return false;
            }
            return place(command.getSecondWord(), command.getCoordinates(), command.getLetters());
            
        }
        System.out.println("Unrecognized Command. Type help for help.");
        System.out.println("\n\n");
        return false;

        
    }

    /**
     * Shuffles the players hand if they want it
     * 
     * @param letters String
     * 
     */
    private void shuffleHand(String letters) {
        if(letters.isEmpty()){
            currPlayer.removeLetters(currPlayer.getHand());
            currPlayer.addLettersToHand(letterBag.getRandomLetters(7));
        } else {
            ArrayList<Tile> shuffles = new ArrayList<>();
            for(int i = 0; i < letters.length(); i ++){
                if(currPlayer.hasLetter(letters.charAt(i))){
                    shuffles.add(Tile.charToTile(letters.charAt(i)));
                    currPlayer.removeLetter(letters.charAt(i));
                }
            }
            currPlayer.addLettersToHand(letterBag.getRandomLetters(letters.length()));
        }
    }

    /**
     * The driver code for all the logic that will decide if any placement is legal or not
     * @param direction String
     * @param cord Coordinates
     * @param word String
     * @return boolean
     */
    private boolean place(String direction, Coordinates cord, String word) {
        isTouching = false;
        boolean finalCheck = false;
        if(isTurnOne && (! cord.getXCoordinate().equals(Coordinates.xCoordinate.H) || ! cord.getYCoordinate().equals(Coordinates.yCoordinate.EIGHT))){
            System.out.println("On turn one, you MUST start at H EIGHT.");
            return false;
        }
        ArrayList<Tile> tilesTaken = new ArrayList<>();
        //this will get rid of the brackets leaving the original word behind
        String temp = word.toLowerCase();
        tempBoard = board;
        if (temp.length() == 1){
            System.out.println("Invalid Entry, words must be longer then 1.");
            return false;
        }
        //check if its a legal word
        if(dictionary.isLegalWord(temp)){
            Coordinates tempCord = new Coordinates(cord.getXCoordinate(),cord.getYCoordinate());
            for(int i = 0; i < temp.length(); i++) {
                //this is only if we need to check right of the x axis
                if (direction.equals("right")) {
                    if(tempBoard.checkFree(tempCord) && currPlayer.hasLetter(temp.charAt(i))){
                        tilesTaken.add(Tile.charToTile(temp.charAt(i)));
                        tempBoard.placeTile(tempCord,currPlayer.removeLetter(temp.charAt(i)));
                    }
                    else if(tempBoard.checkFree(tempCord) && ! currPlayer.hasLetter(temp.charAt(i))){
                        System.out.println("You dont have letter " + temp.charAt(i));
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    else if(! tempBoard.checkFree(tempCord) && Character.toUpperCase(temp.charAt(i)) != tempBoard.getLetter(tempCord)){
                        System.out.println("Word mismatch.");
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    
                    if( ! upDownCheck(tempCord, word)){
                        currPlayer.addLettersToHand(tilesTaken);
                        System.out.println("Invalid Placement");
                        return false;
                    }
                    tempCord = new Coordinates((cord.getXCoordinate().ordinal() + i + 1), cord.getYCoordinate());




                }
                //this will check down the y axis
                if(direction.equals("down")) {
                    if(tempBoard.checkFree(tempCord) && currPlayer.hasLetter(temp.charAt(i))){
                        tilesTaken.add(Tile.charToTile(temp.charAt(i)));
                        tempBoard.placeTile(tempCord,currPlayer.removeLetter(temp.charAt(i)));
                    } else if(tempBoard.checkFree(tempCord) && ! currPlayer.hasLetter(temp.charAt(i))){
                        System.out.println("You dont have letter " + temp.charAt(i));
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    } else if(! tempBoard.checkFree(tempCord) && Character.toUpperCase(temp.charAt(i)) != tempBoard.getLetter(tempCord)){
                        System.out.println("Word mismatch.");
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    if(! leftRightCheck(tempCord, word)){
                        System.out.println("Invalid Placement");
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    
                    tempCord = new Coordinates(cord.getXCoordinate(), (cord.getYCoordinate().ordinal() + 1 + i));

                }
            }
            tempCord = cord;
            if(! isTurnOne){
                for(int i = 0; i < word.length(); i++){
                    if (direction.equals("right")){
                        if(! upDownCheck(tempCord, word)){
                            System.out.println("Invalid Placement");
                            return false;
                        }
                        finalCheck = true;
                        tempCord = new Coordinates((cord.getXCoordinate().ordinal() + i + 1), cord.getYCoordinate());
                        leftRightCheck(cord, word);
                    }
                    if (direction.equals("down")){
                        if (! leftRightCheck(tempCord, word)){
                            System.out.println("Invalid Placement");
                            return false;
                        }
                        finalCheck = true;
                        tempCord = new Coordinates(cord.getXCoordinate(), (cord.getYCoordinate().ordinal() + 1 + i));
                        upDownCheck(cord, word);
                    }
                }
                if(! isTouching){
                    System.out.println("Floating Word.");
                    currPlayer.addLettersToHand(tilesTaken);
                    
                    finalCheck = false;
                    return false;
                }
            } else{
                boolean crossesStart = false;
                for(int i = 0; i < word.length(); i++){
                    if (direction.equals("right")){
                        if (tempCord == new Coordinates(7,7)){
                            crossesStart = true;
                        }
                        tempCord = new Coordinates((cord.getXCoordinate().ordinal() + i + 1), cord.getYCoordinate());
                    }
                    if (direction.equals("down")){
                        if (! leftRightCheck(tempCord, word)){
                            System.out.println("Invalid Placement");
                            return false;
                        }
                        finalCheck = true;
                        tempCord = new Coordinates(cord.getXCoordinate(), (cord.getYCoordinate().ordinal() + 1 + i));
                        upDownCheck(cord, word);
                    }
                }
                if(! crossesStart){
                    System.out.println("The placed word must cross start (H 08).");
                    return false;
                }
                if (direction.equals("right")){
                    leftRightCheck(cord, word);
                }
                else if(direction.equals("down")){
                    upDownCheck(cord, word);
                }
                finalCheck = true;
            }
        }
        else{
            System.out.println("Invalid word.");
            return false;
        }
        if (finalCheck){
            board = tempBoard;
            for (int i = 0; i < word.length(); i++){
                currPlayer.addScore(Tile.charToTile(word.charAt(i)).getScore());
            }
            currPlayer.addLettersToHand(letterBag.getRandomLetters(tilesTaken.size()));
            isTurnOne = false;
            return true;
            
        }
        System.out.println("Unknown error.");
        return false;
    }

    /**
     * Checks left and right of each tile to ensure legal placing
     * @param checkCord Coordinates
     * @return boolean
     */
    private boolean leftRightCheck(Coordinates checkCord, String word){
        String possibleWord = "";
        Coordinates tempCoordinates = null;
        while( ! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            tempCoordinates = checkCord;
            if(checkCord.getXCoordinate().ordinal() >= 1){
                checkCord = new Coordinates((checkCord.getXCoordinate().ordinal() - 1), checkCord.getYCoordinate());
            } else {
                break;
            }
            
        }
        checkCord = tempCoordinates;
        while( ! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            possibleWord+=tempBoard.getLetter(checkCord);
            if(checkCord.getXCoordinate().ordinal() <= 14){
                checkCord = new Coordinates((checkCord.getXCoordinate().ordinal() + 1), checkCord.getYCoordinate());
            }
            else{
                break;
            }
            
        }

        if(((possibleWord.length() > 1) && ! possibleWord.equals(word.toUpperCase()))){
            isTouching = true;
        }

        if(dictionary.isLegalWord(possibleWord)){
            return true;
        }
        return false;
    }

    /**
     * Checks up and down of each tile to ensure legal placing
     * @param checkCord Coordinates
     * @return boolean
     */
    private boolean upDownCheck(Coordinates checkCord, String word){
        String possibleWord = "";
        Coordinates tempCoordinates = null;
        while(! tempBoard.checkFree(checkCord)) {
            //if we are checking up
            tempCoordinates = checkCord;
            if(checkCord.getYCoordinate().ordinal() >= 1){
                checkCord = new Coordinates(checkCord.getXCoordinate(),(checkCord.getYCoordinate().ordinal() - 1));
            }
            else{
                break;
            }
        }
        checkCord = tempCoordinates;
        while(! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            possibleWord+=tempBoard.getLetter(checkCord);
            if(checkCord.getYCoordinate().ordinal() <= 14){
                checkCord = new Coordinates(checkCord.getXCoordinate(),(checkCord.getYCoordinate().ordinal() + 1));
            }
            else{
                break;
            }
            
        }

        if((possibleWord.length()> 1) && ! possibleWord.equals(word.toUpperCase())){
            isTouching = true;
        }
        if(dictionary.isLegalWord(possibleWord.toLowerCase())){
            return true;
        }
        return false;



    }
    private void printHelp() {
        System.out.print("There are 4 different Commands."
        +"2 of them, \'help\' and \'pass\'. These both only require you to input those words alone."
        +"\n\nshuffle, however is more complicated. type \'shuffle (letter1) (letter2)\' to shuffle any number of letters in your hand."
        +"For example, with a hand of g d a e f p q you can type \"shuffle g d a\" which would shuffle the g d and a tiles back into the bag. you can also just type \"shuffle\" to shuffle all your hand."
        +"\n\nHowever, the most complex command is place. place is split into: place <direction> <x Coordinate> <yCoordinate> <word> Direction is right or down."
        +"X coordinates are A to O, not case sensitive. Y coordinates ONE to FIFTEEN. Word, however, is compromised of letters you have in your hand and any tiles already on the board you are going to intersect.");
    }


    /**
     * Starts up the game
     * @param args
     */
    public static void main(String[] args){
        Game game = new Game();
    }
}