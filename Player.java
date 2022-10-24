import java.util.ArrayList;

public class Player {
    
    Integer score;
    ArrayList<LetterBag.Letter> hand;

    
    
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

    public ArrayList<LetterBag.Letter> getHand(){
        return hand;
    }
    public void addLettersToHand(ArrayList<LetterBag.Letter> letters){
        hand.addAll(letters);
    }
    
    public void removeLetters(ArrayList<LetterBag.Letter> letters){
        hand.removeAll(letters);
    }
    public boolean removeLetter(LetterBag.Letter letter){
        return hand.remove(letter);
    }
}
