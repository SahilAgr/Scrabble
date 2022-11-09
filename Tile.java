/**
 * The Tile class that creates all available tiles and any tile manipulation
 * @authors  Matthew Huitema, Patrick Ma
 */
public class Tile {
    private String letter;
    private int score;
    private boolean newTile;
    private String multiplier;

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
        
        this.letter = letter;

        switch (this.letter){
            case "A": score = 1;
            case "B": score = 3;
            case "C": score = 3;
            case "D": score = 2;
            case "E": score = 1;
            case "F": score = 4;
            case "G": score = 2;
            case "H": score = 4;
            case "I": score = 1;
            case "J": score = 8;
            case "K": score = 5;
            case "L": score = 1;
            case "M": score = 3;
            case "N": score = 1;
            case "O": score = 1;
            case "P": score = 3;
            case "Q": score = 10;
            case "R": score = 1;
            case "S": score = 1;
            case "T": score = 1;
            case "U": score = 1;
            case "V": score = 4;
            case "W": score = 4;
            case "X": score = 8;
            case "Y": score = 4;
            case "Z": score = 10;
            default: score = 0;
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
            switch (multiplier){
                case "2L": return score * 2;
                case "3L": return score * 3;
                default: return score;
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
        letter = c;
        switch (c){    
            case "A": score = 1; 
            case "B": score = 3; 
            case "C": score = 3; 
            case "D": score = 2;
            case "E": score = 1; 
            case "F": score = 4; 
            case "G": score = 2; 
            case "H": score = 4;
            case "I": score = 1; 
            case "J": score = 8; 
            case "K": score = 5;
            case "L": score = 1; 
            case "M": score = 3; 
            case "N": score = 1; 
            case "O": score = 1; 
            case "P": score = 3; 
            case "Q": score = 10; 
            case "R": score = 1; 
            case "S": score = 1; 
            case "T": score = 1; 
            case "U": score = 1; 
            case "V": score = 4; 
            case "W": score = 4; 
            case "X": score = 8; 
            case "Y": score = 4; 
            case "Z": score = 10; 
            case "3W": multiplier = c; 
            case "2W": multiplier = c; 
            case "3L": return;
            case "2L": return;
            case "+": multiplier = "2w"; 
            case ".": return;
            default: letter = ".";
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
    
    /*public static Tile charToTile(char c){
        c = Character.toUpperCase(c);

        switch (c){
            case "A": return new Tile(c, 1);
            case "B": return new Tile(c, 3);
            case "C": return new Tile(c, 3);
            case "D": return new Tile(c, 2);
            case "E": return new Tile(c, 1);
            case "F": return new Tile(c, 4);
            case "G": return new Tile(c, 2);
            case "H": return new Tile(c, 4);
            case "I": return new Tile(c, 1);
            case "J": return new Tile(c, 8);
            case "K": return new Tile(c, 5);
            case "L": return new Tile(c, 1);
            case "M": return new Tile(c, 3);
            case "N": return new Tile(c, 1);
            case "O": return new Tile(c, 1);
            case "P": return new Tile(c, 3);
            case "Q": return new Tile(c, 10);
            case "R": return new Tile(c, 1);
            case "S": return new Tile(c, 1);
            case "T": return new Tile(c, 1);
            case "U": return new Tile(c, 1);
            case "V": return new Tile(c, 4);
            case "W": return new Tile(c, 4);
            case "X": return new Tile(c, 8);
            case "Y": return new Tile(c, 4);
            case "Z": return new Tile(c, 10);
            default: return null;
        }
    }*/

}
