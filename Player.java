import java.util.ArrayList;

public class Player {
    
    Integer score;
    ArrayList<Tile> hand;

    
    
    public Player(){
        score = 0;
        hand = new ArrayList<>();
    }

    public void addScore(Integer score){
        this.score += score;
    }
    public Integer getScore(){
        return score;
    }

    public ArrayList<Tile> getHand(){
        return hand;
    }
    public void addLettersToHand(ArrayList<Tile> letters){
        hand.addAll(letters);
    }
    
    public void removeLetters(ArrayList<Tile> letters){
        hand.removeAll(letters);
    }
    public Tile removeLetter(char c){
        Tile tile = Tile.charToTile(c);
        if(hand.remove(tile)){
            return tile;
        }
        return null;
    }
    public boolean hasLetter(char c){
        return hand.contains(c);
    }
}
