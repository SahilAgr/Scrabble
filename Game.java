import java.util.ArrayList;
import java.util.List;



/**
 * The Game class that will drive the whole game including creating the board and the players
 * @authors  Matthew Huitema, Sahil Agrawal, Patrick Ma
 */

public class Game {
    private List<Player> players;
    private Player currPlayer;
    private Board board;
    private LetterBag letterBag;
    private int countdown;


    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey

    /**
     * The constructor for the game that will create new players, the borad and all the letters
     */
    public Game(){
        this.board = new Board();
        this.letterBag = new LetterBag();
        this.players = new ArrayList<>();

        
        for(Player p:players){
            p.addLettersToHand(letterBag.getRandomLetters(7));
        }
        turnOrder();
    }

    /**
     * The turn order for the players that will also display player information
     */
    private void turnOrder(){
        boolean gameOver = false;
        
        while (! gameOver){
            gameOver = progressChecker();
        }
        
        for(Player p: players){
            //something that displays how people did
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

    public boolean place(String direction, Coordinates coords, String word, boolean b){
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
    public void shuffleHand(String letters) {
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

    public void addPlayer(Player player){
        this.players.add(player);
    }

    /**
     * Starts up the game
     * @param args
     */
    public static void main(String[] args){
        Game game = new Game();
    }

    public void addScrabbleView(BoardFrame boardFrame) {
    }

    public Player getCurrPlayer() {
        return null;
    }
}