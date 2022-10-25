/**
 * The Board Class that creates the board in the terminal
 * The Board is made up of Tiles
 */
public class Board {

    static Tile[][] gameBoard = new Tile[15][15];//in future use nested hashmap

    /**
     * The constructor for the Board class
     */
    public Board() {
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[y][x] = new Tile();
            }
        }
        gameBoard[7][7].setLetter('+');
    }

    /**
     * Returns the letter at a specific tile coordinate
     * @param coords Coordinate
     * @return char
     */
    public char getLetter(Coordinates coords) {
        return gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter();
    }

    /**
     * Checks if the tile is free at a specific coordinate
     * @param coords Coordinates
     * @return boolean
     */
    public boolean checkFree(Coordinates coords) {
        return gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter()=='.' || gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter()=='+' ;
    }

    /**
     * Places a letter on a tile
     * @param coords Coordinates
     * @param tile Tile
     */
    public void placeTile(Coordinates coords, Tile tile){
        if (gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter() == '.'|| gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter()=='+' ){
            gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()] = tile;
        }else{
            System.out.println("Tile cannot be placed here");
        }
    }

    /**
     * Prints the board in the console
     */
    public void printBoard(){
        int leftIndex = 0;
        System.out.println("   A  B  C  D  E  F  G  H  I  J  K  L  M  N  O");
        for(int x=0 ; x<15 ; x++){
            System.out.print(String.format("%02d ", ++leftIndex));
            for (int y=0 ; y<15 ; y++) {
                System.out.print(gameBoard[y][x].getLetter()+"  ");
            }
            System.out.println();
        }System.out.println();
    }

    
}
