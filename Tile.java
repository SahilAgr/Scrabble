
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
        multiplier = "none";
        this.letter = letter;
        if(letter.length() == 1){
            Character c = letter.charAt(0);
            switch (c){
                case 'A': score = 1; break;
                case 'B': score = 3; break;
                case 'C': score = 3; break;
                case 'D': score = 2; break;
                case 'E': score = 1; break;
                case 'F': score = 4; break;
                case 'G': score = 2; break;
                case 'H': score = 4; break;
                case 'I': score = 1; break;
                case 'J': score = 8; break;
                case 'K': score = 5; break;
                case 'L': score = 1; break;
                case 'M': score = 3; break;
                case 'N': score = 1; break;
                case 'O': score = 1; break;
                case 'P': score = 3; break;
                case 'Q': score = 10; break;
                case 'R': score = 1; break;
                case 'S': score = 1; break;
                case 'T': score = 1; break;
                case 'U': score = 1; break;
                case 'V': score = 4; break;
                case 'W': score = 4; break;
                case 'X': score = 8; break;
                case 'Y': score = 4;break;
                case 'Z': score = 10; break;
                case '+': multiplier = "2w"; break;
                default: score = 0; break;
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
                case 'A': score = 1; break;
                case 'B': score = 3; break;
                case 'C': score = 3; break;
                case 'D': score = 2; break;
                case 'E': score = 1; break;
                case 'F': score = 4; break;
                case 'G': score = 2; break;
                case 'H': score = 4; break;
                case 'I': score = 1; break;
                case 'J': score = 8; break;
                case 'K': score = 5; break;
                case 'L': score = 1; break;
                case 'M': score = 3; break;
                case 'N': score = 1; break;
                case 'O': score = 1; break;
                case 'P': score = 3; break;
                case 'Q': score = 10; break;
                case 'R': score = 1; break;
                case 'S': score = 1; break;
                case 'T': score = 1; break;
                case 'U': score = 1; break;
                case 'V': score = 4; break;
                case 'W': score = 4; break;
                case 'X': score = 8; break;
                case 'Y': score = 4;break;
                case 'Z': score = 10; break;
                case '+': multiplier = "2w"; break;
                default: score = 0; break;
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
        Tile tile = new Tile("z");
        System.out.println(tile.getScore());
        System.out.println(tile.getMultiplier());
    }
}
