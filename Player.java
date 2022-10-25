import java.util.ArrayList;

/**
 * The Player Class
 */
public class Player {
    
    Integer score;
    ArrayList<Tile> hand;
    String name;

    /**
     * The constructor for the Player class
     * @param name String
     */
    public Player(String name){
        score = 0;
        this.name = name;
        hand = new ArrayList<>();
    }

    /**
     * Adds a score to the player
     * @param score Integer
     */
    public void addScore(Integer score){
        this.score += score;
    }

    /**
     * Returns a score of the player
     * @return Integer
     */
    public Integer getScore(){
        return score;
    }

    /**
     * Returns the name of the player
     * @return String
     */
    public String getName(){
        return name;
    }

    /**
     * Returns what hand the player has
     * @return ArrayList<Tile></Tile>
     */
    public ArrayList<Tile> getHand(){
        return hand;
    }

    /**
     * Adds a letter to the players hand
     * @param letters ArrayList
     */
    public void addLettersToHand(ArrayList<Tile> letters){
        hand.addAll(letters);
    }

    /**
     * Removes all letters from the players hand
     * @param letters ArrayList
     */
    public void removeLetters(ArrayList<Tile> letters){
        hand.removeAll(letters);
    }

    /**
     * Removes a specifi letter from the players hand
     * @param c char
     * @return Tile
     */
    public Tile removeLetter(char c){
        Tile tile = Tile.charToTile(c);
        if(hand.remove(tile)){
            return tile;
        }
        return null;
    }

    /**
     * Checks if the player has a specific letter
     * @param c char
     * @return boolean
     */
    public boolean hasLetter(char c){
        return hand.contains(c);
    }
}
