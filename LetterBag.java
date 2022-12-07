import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

/**
 * The LetterBag Class that holds all the letters available for the game
 * @author  Matthew Huitema
 */
public class LetterBag implements Serializable{
    
    public static final long serialVersionUID = 1L;
    
    private List<Tile> letterBag;

    /**
     * The constructor for the LetterBag which creates enough letters and assigns it the proper points
     */
    public LetterBag(){
        
        letterBag = new ArrayList<>();

        for(int i = 0;i < 9;i++){
            letterBag.add(new Tile("A"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("B"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("C"));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile("D"));
        } for(int i = 0;i < 12;i++){
            letterBag.add(new Tile("E"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("F"));
        } for(int i = 0;i < 3;i++){
            letterBag.add(new Tile("G"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("H"));
        } for(int i = 0;i < 9;i++){
            letterBag.add(new Tile("I"));
        } letterBag.add(new Tile("J"));
        letterBag.add(new Tile("K"));
        for(int i = 0;i < 4;i++){
            letterBag.add(new Tile("L"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("M"));
        } for(int i = 0;i < 6;i++){
            letterBag.add(new Tile("N"));
        } for(int i = 0;i < 8;i++){
            letterBag.add(new Tile("O"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("P"));
        } letterBag.add(new Tile("Q"));
        for(int i = 0;i < 6;i++){
            letterBag.add(new Tile("R"));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile("S"));
        } for(int i = 0;i < 6;i++){
            letterBag.add(new Tile("T"));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile("U"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("V"));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("W"));
        } letterBag.add(new Tile("X"));
        for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("Y"));
        } letterBag.add(new Tile("Z"));
        for(int i = 0;i < 2;i++){
            letterBag.add(new Tile("*"));
        }

    }

    /**
     * Returns random letteres from the bag
     * @param amount Integer
     * @return Arraylist<Tiles></Tiles>
     */
    public ArrayList<Tile> getRandomLetters(Integer amount){
        ArrayList<Tile> randomLetters = new ArrayList<>();
        Random random = new Random();
        if(amount > letterBag.size()){
            amount = letterBag.size();
        }
        for(int i = 0; i < amount;i++){
            randomLetters.add(letterBag.remove(random.nextInt(letterBag.size())));
        }
        return randomLetters;
    }

    /**
     * Returns the letters that are in the bag
     * @return List<Tile></Tile>
     */
    public List<Tile> getLetters(){
        return letterBag;
    }

    /**
     * Checks if the letter bag is empty or not
     * @return boolean
     */
    public boolean isEmpty(){
        return (letterBag.size() == 0);
    }
}
