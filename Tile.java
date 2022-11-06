/**
 * The Tile class that creates all available tiles and any tile manipulation
 * @authors  Matthew Huitema, Patrick Ma
 */
public class Tile {
    private char letter;
    private int score;
    private boolean newTile;

    /**
     * The constructor for the tile Class
     * @param letter char
     * @param score int
     */
    public Tile(char letter, int score, boolean placed) {
        this.letter = letter; 
        this.score = score;
        this.newTile = placed;
    }
    public Tile(char letter, int score){
        this.letter = letter; 
        this.score = score;
        this.newTile = false;
    }

    public Tile() {
        letter = '.';
        score = 0;
    }

    /**
     * Returns the letter on a tile
     * @return char
     */
    public char getLetter(){
        return letter;
    }

    /**
     * Returns the score on the tile
     * @return int
     */
    public int getScore(){
        return score;
    }


    public void setLetter(char c){
        letter = c;
    }

    public boolean getNewTile(){
        return newTile;
    }
    
    public void setNewTileToFalse(){
        newTile = false;
    }
    
    /**
     * Turns the char into a tile
     * @param c char
     * @return Tile
     */
    
    public static Tile charToTile(char c){
        c = Character.toUpperCase(c);

        switch (c){
            case 'A': return new Tile(c, 1);
            case 'B': return new Tile(c, 3);
            case 'C': return new Tile(c, 3);
            case 'D': return new Tile(c, 2);
            case 'E': return new Tile(c, 1);
            case 'F': return new Tile(c, 4);
            case 'G': return new Tile(c, 2);
            case 'H': return new Tile(c, 4);
            case 'I': return new Tile(c, 1);
            case 'J': return new Tile(c, 8);
            case 'K': return new Tile(c, 5);
            case 'L': return new Tile(c, 1);
            case 'M': return new Tile(c, 3);
            case 'N': return new Tile(c, 1);
            case 'O': return new Tile(c, 1);
            case 'P': return new Tile(c, 3);
            case 'Q': return new Tile(c, 10);
            case 'R': return new Tile(c, 1);
            case 'S': return new Tile(c, 1);
            case 'T': return new Tile(c, 1);
            case 'U': return new Tile(c, 1);
            case 'V': return new Tile(c, 4);
            case 'W': return new Tile(c, 4);
            case 'X': return new Tile(c, 8);
            case 'Y': return new Tile(c, 4);
            case 'Z': return new Tile(c, 10);
            default: return null;
        }
    }

}
