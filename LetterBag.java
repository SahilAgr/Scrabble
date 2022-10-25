import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The LetterBag Class
 */
public class LetterBag {
    
    private List<Tile> letterBag;

    /**
     * The constructor for the LetterBag which creates enough letters and assigns it the proper points
     */
    public LetterBag(){
        //its also disguisting. I probably should just use a json. i will do this tomorrow.
        letterBag = new ArrayList<>();
        for(int i = 0;i < 9;i++){
            letterBag.add(new Tile('A',1));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('B',3));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('C',3));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile('D',2));
        } for(int i = 0;i < 12;i++){
            letterBag.add(new Tile('E',1));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('F',4));
        } for(int i = 0;i < 3;i++){
            letterBag.add(new Tile('G',2));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('H',4));
        } for(int i = 0;i < 9;i++){
            letterBag.add(new Tile('I',1));
        } letterBag.add(new Tile('J',8));
        letterBag.add(new Tile('K',5));
        for(int i = 0;i < 4;i++){
            letterBag.add(new Tile('L',1));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('M',3));
        } for(int i = 0;i < 6;i++){
            letterBag.add(new Tile('N',1));
        } for(int i = 0;i < 8;i++){
            letterBag.add(new Tile('O',1));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('P',3));
        } letterBag.add(new Tile('Q',10));
        for(int i = 0;i < 6;i++){
            letterBag.add(new Tile('R',1));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile('S',1));
        } for(int i = 0;i < 6;i++){
            letterBag.add(new Tile('T',1));
        } for(int i = 0;i < 4;i++){
            letterBag.add(new Tile('U',1));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('V',4));
        } for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('W',4));
        } letterBag.add(new Tile('X',4));
        for(int i = 0;i < 2;i++){
            letterBag.add(new Tile('Y',4));
        } letterBag.add(new Tile('Z',10));
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
    public boolean isEmpty(){
        return (letterBag.size() == 0);
    }
}
