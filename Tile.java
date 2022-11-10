import java.rmi.StubNotFoundException;

/**
 * The Tile class that creates all available tiles and any tile manipulation
 * @authors  Matthew Huitema, Patrick Ma
 */
public class Tile {
    private String letter;
    private int score;
    private boolean newTile;
    private String multiplier;
    private String oldLetter;

    /**
     * The constructor for the tile Class
     * @param letter char
     * @param score int
     */
    public Tile(String letter, int score, boolean placed) {
        this.letter = letter.toUpperCase(); 
        this.score = score;
        this.newTile = placed;
    }
    public Tile(String letter, int score, String multipl){
        this.letter = letter.toUpperCase();  
        this.score = score;
        this.newTile = false;
        this.multiplier = multipl;
    }

    public Tile(String letter){
        letter = letter.toUpperCase();
        multiplier = null;
        this.letter = letter;
        if(letter.length() == 1){
            Character c = letter.charAt(0);
            System.out.println("hi");
            System.out.println(c);
            switch (c){
                case 'A': score = 1;
                case 'B': score = 3;
                case 'C': score = 3;
                case 'D': score = 2;
                case 'E': score = 1;
                case 'F': score = 4;
                case 'G': score = 2;
                case 'H': score = 4;
                case 'I': score = 1;
                case 'J': score = 8;
                case 'K': score = 5;
                case 'L': score = 1;
                case 'M': score = 3;
                case 'N': score = 1;
                case 'O': score = 1;
                case 'P': score = 3;
                case 'Q': score = 10;
                case 'R': score = 1;
                case 'S': score = 1;
                case 'T': score = 1;
                case 'U': score = 1;
                case 'V': score = 4;
                case 'W': score = 4;
                case 'X': score = 8;
                case 'Y': score = 4;
                case 'Z': score = 10;
                case '+': multiplier = "2w";
                default: score = 0;
            }
        }
        else {
            if(this.letter.equals("3L")){
                multiplier = this.letter;
            } else if(this.letter.equals("2L")){
                multiplier = this.letter;
            } else if(this.letter.equals("3W")){
                multiplier = this.letter;
            } else if(this.letter.equals("2W")){
                multiplier = this.letter;
            }
        }
        
    }

    /**
     * Returns the letter on a tile
     * @return char
     */
    public String getString(){
        return this.letter;
    }

    /**
     * Returns the score on the tile
     * @return int
     */
    public int getScore(){
        if (newTile){
            if(multiplier.equals("2L")){
                return this.score * 2;
            }
            else if(multiplier.equals("3L")){
                return this.score * 3;
            }
            else{
                return score;
            }
        }
        
        else{
            return score;
        }
    }

    public String getMultiplier(){
        return multiplier;
    }

    public void setMultiplier(String multi){
        multiplier = multi;
    }

    public void setLetter(String c){
        c = c.toUpperCase();
        oldLetter = letter;
        letter = c;
        if(letter.length() == 1){
            char d = letter.charAt(0);
            switch (d){
                case 'A': score = 1;
                case 'B': score = 3;
                case 'C': score = 3;
                case 'D': score = 2;
                case 'E': score = 1;
                case 'F': score = 4;
                case 'G': score = 2;
                case 'H': score = 4;
                case 'I': score = 1;
                case 'J': score = 8;
                case 'K': score = 5;
                case 'L': score = 1;
                case 'M': score = 3;
                case 'N': score = 1;
                case 'O': score = 1;
                case 'P': score = 3;
                case 'Q': score = 10;
                case 'R': score = 1;
                case 'S': score = 1;
                case 'T': score = 1;
                case 'U': score = 1;
                case 'V': score = 4;
                case 'W': score = 4;
                case 'X': score = 8;
                case 'Y': score = 4;
                case 'Z': score = 10;
                case '+': multiplier = "2w";
                default: score = 0;
            }
        }
        else {
            if(this.letter.equals("3L")){
                multiplier = this.letter;
            } else if(this.letter.equals("2L")){
                multiplier = this.letter;
            } else if(this.letter.equals("3W")){
                multiplier = this.letter;
            } else if(this.letter.equals("2W")){
                multiplier = this.letter;
            }
        }
    }

    public boolean getNewTile(){
        return newTile;
    }
    
    public void setNewTile(boolean b){
        newTile = b;
    }
    
    /**
     * Turns the char into a tile
     * @param c char
     * @return Tile
     */
    

    public void resetTile(){
        this.setLetter(oldLetter);
    }
    public static void main(String[] args) {
        Tile tile = new Tile("t");
        System.out.println(tile.getScore());
        System.out.println(tile.getString().length());
    }
}
