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
    private Board board;
    private Parser parser;
    private LetterBag letterBag;
    private int countdown;
    private int numPlayers;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    /**
     * The constructor for the game that will create new players, the borad and all the letters
     */
    public Game(){
        this.parser = new Parser();
        this.board = new Board();
        this.letterBag = new LetterBag();
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
                        System.out.print(t.getString() +" ");
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


    private boolean place(String direction, Coordinates coords, String word){
        Placement place = board.checkPlacement(coords, word, direction, false, currPlayer);
        System.out.println(place.isLegalPlace());
        if (place.isLegalPlace()){
            System.out.println(place.getErrorMessage());
            System.out.println(place.getScore());
            return true;
        }
        System.out.println(place.getErrorMessage());
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
                if(currPlayer.hasLetter(String.valueOf(letters.charAt(i)))){
                    shuffles.add(new Tile(String.valueOf(letters.charAt(i))));
                    currPlayer.removeLetter(String.valueOf(letters.charAt(i)));
                }
            }
            currPlayer.addLettersToHand(letterBag.getRandomLetters(letters.length()));
        }
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
    }

    public void addPlayer(Player player){
        this.players.add(player);
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