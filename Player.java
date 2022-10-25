import java.util.ArrayList;

public class Player {
    
    Integer score;
    ArrayList<Tile> hand;
    String name;
    
    
    public Player(String name){
        score = 0;
        this.name = name;
        hand = new ArrayList<>();
    }

    public void addScore(Integer score){
        this.score += score;
    }
    public Integer getScore(){
        return score;
    }
    public String getName(){
        return name;
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
        c = Character.toUpperCase(c);
        for(Tile t: hand){
            if (c == (t.getLetter())){
                hand.remove(t);
                return t;
            }
        }
        return null;
    }
    public boolean hasLetter(char c){
        c = Character.toUpperCase(c);
        for(Tile t: hand){
            if (c == (t.getLetter())){
                return true;
            }
        }
        return false;
    }
}
