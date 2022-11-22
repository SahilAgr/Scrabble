import java.awt.*;
import java.util.ArrayList;
import java.util.List;



/**
 * The Game class that will drive the whole game including creating the board and the players
 * @authors  Matthew Huitema, Sahil Agrawal, Patrick Ma
 */

public class Game {
    private ArrayList<Player> players;
    private Player currPlayer;
    private int currPlayerIndex;
    private Board board;
    private LetterBag letterBag;
    private int countdown;
    private List<ScrabbleView> views;


    /**
     * The constructor for the game that will create new players, the board and all the letters
     */
    public Game(ArrayList<Player> players){
        this.board = new Board();
        this.letterBag = new LetterBag();
        this.players = players;
        this.views = new ArrayList<>();

        currPlayerIndex = 0;
        currPlayer = players.get(currPlayerIndex);

        for(Player p:players){
            p.addLettersToHand(letterBag.getRandomLetters(7));
        }
    }


    /**
     * place either places a letter or makes a 
     * 
     * @param direction
     * @param coords
     * @param word
     * @param b
     */
    public void place(String direction, Coordinates coords, String word, boolean b){
        Placement place = board.checkPlacement(coords, word, direction, b, currPlayer);
        System.out.println(word);
        System.out.println(direction);
        System.out.println(place.getErrorMessage());
        if(!b){
            if(place.isLegalPlace()){
                if(currPlayerIndex < players.size()-1){
                    currPlayerIndex++;
                }else{
                    currPlayerIndex = 0;
                }
                currPlayer = players.get(currPlayerIndex);
            }
        }

        for (ScrabbleView view: views){
            view.update(new GameEvent(this, place, currPlayer, board));
        }

    }

    /**
     * Shuffles the players hand if they want it
     * 
     * @param letters String
     * 
     */
    public void shuffleHand(String letters) {
        String shuffled = "";
        if(letters.isEmpty()){
            currPlayer.removeLetters(currPlayer.getHand());
            currPlayer.addLettersToHand(letterBag.getRandomLetters(7));
        } else {
            ArrayList<Tile> shuffles = new ArrayList<>();
            for(int i = 0; i < letters.length(); i ++){
                if(currPlayer.hasLetter(String.valueOf(letters.charAt(i)))){
                    shuffles.add(new Tile(String.valueOf(letters.charAt(i))));
                    Tile tile = currPlayer.removeLetter(String.valueOf(letters.charAt(i)));
                    shuffled += tile.getString() + " ";
                }
            }
            currPlayer.addLettersToHand(letterBag.getRandomLetters(letters.length()));
        }
        if(currPlayerIndex < players.size()-1){
            currPlayerIndex++;
        }else{
            currPlayerIndex = 0;
        }
        currPlayer = players.get(currPlayerIndex);
        Placement place = new Placement(false, "Shuffled these letters back into the bag: " + shuffled, 0);
        this.turnOrder(place);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void addScrabbleView(BoardFrame boardFrame) {
        views.add(boardFrame);
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    private void turnOrder(Placement place){
        System.out.println("turnorder");
        for (Tile t: currPlayer.getHand()){

            System.out.print(t.getString());
        }
        for (ScrabbleView view: views){
            view.update(new GameEvent(this, place, currPlayer, board));
        }
        if(letterBag.isEmpty()){
            //something that tells the users that this is their last turn?

            countdown -= 1;
            if (countdown == 0){
                for (ScrabbleView view: views){
                    view.gameOver(players);
                }
            }
        }
    }

    public void passTurn(){
        if(currPlayerIndex < players.size()-1){
            currPlayerIndex++;
        }else{
            currPlayerIndex = 0;
        }
        currPlayer = players.get(currPlayerIndex);
        Placement place = new Placement(false, "You passed your turn.", 0);
        turnOrder(place);
    }

    public Board getBoard(){
        return board;
    }

}