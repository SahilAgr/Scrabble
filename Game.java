import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




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
    private Integer culmativeScore;

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
        tempBoard = board;

    }

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

    //checks how many tiles are left in the letterbag, then begins a countdown equal to the number of players. when that reaches zero, the game is over.
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


    private boolean processCommand(Command command){
        if(command.invalidCommand()){
            System.out.println("invalid command");
            //print help message here?
        }
        else if(command.commandWord.equals("help")){
            printHelp();
            return false;
        }
        else if (command.commandWord.equals("pass")){
            passPlayers();
            return true;
        }
        else if (command.commandWord.equals("shuffle")){
            shuffleHand(command.getLetters());
            return true;
        }
        else if (command.commandWord.equals("place")){
            return place(command.getSecondWord(), command.getCoordinates(), command.getLetters());
            
        }
        System.out.println("Unrecognized Command. Type help for help.");
        System.out.println("\n\n");
        return false;

        
    }

    private void shuffleHand(String letters) {
        ArrayList<Tile> shuffles = new ArrayList<>();
        for(int i = 0; i < letters.length(); i ++){
            if(currPlayer.hasLetter(letters.charAt(i))){
                shuffles.add(Tile.charToTile(letters.charAt(i)));
                currPlayer.removeLetter(letters.charAt(i));
            }
        }
        currPlayer.addLettersToHand(letterBag.getRandomLetters(letters.length()));
    }

    private boolean place(String direction, Coordinates cord, String word) {
        culmativeScore = 0;
        isTouching = false;
        if(isTurnOne && (! cord.getXCoordinate().equals(Coordinates.xCoordinate.H) || ! cord.getYCoordinate().equals(Coordinates.yCoordinate.EIGHT))){
            System.out.println("On turn one, you MUST start at H EIGHT.");
            return false;
        }
        ArrayList<Tile> tilesTaken = new ArrayList<>();
        //this will get rid of the brackets leaving the original word behind
        String temp = word.replaceAll("[^a-zA-Z0-9]","");
        tempBoard = board;

        //check if its a legal word

        if(dictionary.isLegalWord(temp)){
            Coordinates tempCord = new Coordinates(cord.getXCoordinate(),cord.getYCoordinate());
            for(int i = 0; i < temp.length(); i++) {
                //check if the tiles are free or not
                System.out.println(direction);
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
                    } else {
                        isTouching = true;
                    }
                    
                    if( ! upDownCheck(tempCord)){
                        System.out.println("test");
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    tempCord = new Coordinates((cord.getXCoordinate().ordinal() + i + 2), cord.getYCoordinate());
                    //check that if the letter is already on the board 



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
                    } else {
                        isTouching = true;
                    }
                    if(! leftRightCheck(tempCord)){
                        System.out.println("test");
                        currPlayer.addLettersToHand(tilesTaken);
                        return false;
                    }
                    
                    tempCord = new Coordinates(cord.getXCoordinate(), (cord.getYCoordinate().ordinal() + 2 + i));

                }
            }
            tempCord = cord;
            culmativeScore = 0;
            if(!isTurnOne){
                if(! upDownCheck(cord) || !leftRightCheck(cord) || ! isTouching){
                    currPlayer.addLettersToHand(tilesTaken);
                    System.out.println("Invalid Placement.");
                    return false;
                    
                }
            }

                
        }
        else{
            System.out.println("Invalid word.");
            return false;
        }
        board = tempBoard;
        for(Tile t: tilesTaken){
            currPlayer.addScore(t.getScore());
        }
        currPlayer.addScore(culmativeScore);
        currPlayer.addLettersToHand(letterBag.getRandomLetters(tilesTaken.size()));
        isTurnOne = false;
        return true;

}

    private boolean leftRightCheck(Coordinates checkCord){
        String possibleWord = "";
        Coordinates tempCoordinates = null;
        while( ! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            tempCoordinates = checkCord;
            checkCord = new Coordinates((checkCord.getXCoordinate().ordinal()), checkCord.getYCoordinate());
            
        }
        checkCord = tempCoordinates;
        while( ! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            possibleWord+=tempBoard.getLetter(checkCord);
            checkCord = new Coordinates((checkCord.getXCoordinate().ordinal() + 2), checkCord.getYCoordinate());
            
        }

        if(possibleWord.length() > 1){
            isTouching = true;
        }
        if(possibleWord.length() <= 1 || dictionary.isLegalWord(possibleWord)){
            for(int i = 0; i < possibleWord.length(); i++){
                culmativeScore += Tile.charToTile(possibleWord.charAt(i)).getScore();
            }
            return true;
        }
        return false;
    }
    private boolean upDownCheck(Coordinates checkCord){
        String possibleWord = "";
        Coordinates tempCoordinates = null;
        while(! tempBoard.checkFree(checkCord)) {
            //if we are checking up
            tempCoordinates = checkCord;
            checkCord = new Coordinates(checkCord.getXCoordinate(),(checkCord.getYCoordinate().ordinal()));
        }
        checkCord = tempCoordinates;
        while(! tempBoard.checkFree(checkCord)) {
            //if we are checking left
            possibleWord+=tempBoard.getLetter(checkCord);
            checkCord = new Coordinates(checkCord.getXCoordinate(),(checkCord.getYCoordinate().ordinal()+2));
            
        }

        if(possibleWord.length()> 1){
            isTouching = true;
        }

        if(possibleWord.length() <= 1 || dictionary.isLegalWord(possibleWord)){
            return true;
        }
        return false;



    }


private void passPlayers() {
        //probably should have some way to autoprint next player's letters and score and boardstate.
    }

    private void printHelp() {
        System.out.print("There are 4 different Commands."
        +"2 of them, \'help\' and \'pass\'. These both only require you to input those words alone."
        +"shuffle, however is more complicated. type \'shuffle (letter1) (letter2)\' to shuffle any number of letters in your hand."
        +"For example, with a hand of g d a e f p q you can type \"shuffle g d a\" which would shuffle the g d and a tiles back into the bag."
        +"However, the most complex command is place. place is split into: place <direction> (<x Coordinate> <yCoordinate> <letter>) (<x Coordinate> <yCoordinate> <letter>)"
        +"X coordinates are A to O, not case sensitive. Y coordinates ONE to FIFTEEN. Letter is a letter in your hand.");
    }


    public static void main(String[] args){
        Game game = new Game();
    }
}