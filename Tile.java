import java.awt.Color;
/**
 * The Tile class that creates all available tiles and any tile manipulation
 * @authors  Matthew Huitema, Patrick Ma
 */
public class Tile {
    private String letter;
    private int score;
    private boolean newTile;
    private Color colour;
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
    public Tile(String letter, int score, Color colour){
        this.letter = letter.toUpperCase();  
        this.score = score;
        this.newTile = false;
        this.colour = colour;
    }

    public Tile(String letter){
        letter = letter.toUpperCase();
        colour = new Color(233,224,206);
        oldLetter = "";
        this.letter = letter;
        if(letter.length() == 1){
            Character c = letter.charAt(0);
            c = Character.toUpperCase(c);
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
                case '*': score = 0; break;
                case '+': colour = new Color(249, 188, 166); break;
                default: score = 0; break;
            }
        }
        else {
            if(this.letter.equals("3L")){
                colour = new Color(65,159,184);
            } else if(this.letter.equals("2L")){
                colour = new Color(194, 214, 213);
            } else if(this.letter.equals("3W")){
                colour = new Color(249, 106, 79);
            } else if(this.letter.equals("2W")){
                colour = new Color(249, 188, 166);
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
            if(colour == new Color(194, 214, 213)){
                return this.score * 2;
            }
            else if(colour == new Color(65,159,184)){
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

    public Color getColour(){
        return colour;
    }

    public void setColour(Color multi){
        colour = multi;
    }

    public void setLetter(String c){
        colour = new Color(233,224,206);
        if(oldLetter.length() != 0){
            c = c.toUpperCase();
        }
        oldLetter = letter;
        letter = c;
        if(letter.length() == 1){
            char d = letter.charAt(0);
            d = Character.toUpperCase(d);
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
                case 'Y': score = 4; break;
                case 'Z': score = 10; break;
                case '*': score = 0; break;
                case '+': colour = new Color(249, 188, 166); break;
                default: score = 0; letter = "."; break;
            }
        }
        else {

            if(letter.equals("3L")){
                colour = new Color(65,159,184);
            } else if(this.letter.equals("2L")){
                colour = new Color(194, 214, 213);
            } else if(this.letter.equals("3W")){
                colour = new Color(249, 106, 79);
            } else if(this.letter.equals("2W")){
                colour = new Color(249, 188, 166);
            }
            else {
                score = 0; 
                letter = ".";
            }
        }
    }

    public boolean getNewTile(){
        return newTile;
    }
    
    public void setNewTile(boolean b){
        newTile = b;
    }
    

    

    public void resetTile(){
        this.setLetter(oldLetter);
    }
    public static void main(String[] args) {
        Tile tile = new Tile("3w");
        System.out.println(tile.getScore());
        System.out.println(tile.getColour());
    }
}
