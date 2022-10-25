import java.util.ArrayList;

public class Player {
    
    Integer score;
    ArrayList<Tile> hand;

    
    
    public Player(){
        score = 0;
        hand = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            //hand.add()
        }
    }

    public void addScore(Integer score){
        this.score += score;
    }
    public Integer getScore(){
        return score;
    }
/*
    public ArrayList<LetterBag.Letter> getHand(){
        return hand;
    }
    public void addLettersToHand(ArrayList<LetterBag.Letter> letters){
        hand.addAll(letters);
    }
  */
    public void removeLetters(ArrayList<LetterBag.Letter> letters){
        hand.removeAll(letters);
    }

    public Tile removeLetter(char letter){
        int index = 0;
        for(Tile tile: hand){
            if(tile.letter == letter){
                break;
            }
            index++;
        }
        return hand.remove(index);

    }
    public boolean hasLetter(char letter){
        return hand.contains(letter);
    }
}
