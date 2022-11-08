import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Board Class that creates the board in the terminal
 * The Board is made up of Tiles
 * @author Patrick Ma
 */
public class Board {

    private Tile[][] gameBoard = new Tile[15][15];//in future use nested hashmap
    private boolean isTouching;

    /**
     * The constructor for the Board class
     *
     */
    public Board() {
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[y][x] = new Tile('.', 0);
            }
        }
        gameBoard[7][7].setLetter('+');
    }
    public Board(Tile[][] tileArray){
        gameBoard = tileArray;
    }

    /**
     * Returns the letter at a specific tile coordinate
     * @param coords Coordinate
     * @return char
     */
    public char getLetter(Coordinates coords) {
        return gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter();
    }

    public Tile getTile(Coordinates coords) {
        return gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()];
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
     * 
     * @return boolean
     */
    public boolean placeTile(Coordinates coords, Tile tile){
        if (gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter() == '.'|| gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getLetter()=='+' ){
            gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()] = tile;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Prints the board in the console. Still useful for testing purposes.
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
    private HashMap<Coordinates, Tile> rightLeftIterator(Coordinates startingCords){
        HashMap<Coordinates, Tile> iterator = new HashMap<>();
        Coordinates tempCoordinates = null;
        while( ! this.checkFree(startingCords)) {
            //if we are checking left
            tempCoordinates = startingCords;
            if(startingCords.getXCoordinate().ordinal() >= 1){
                startingCords.setXCoordinate(startingCords.getXCoordinate().ordinal() - 1);
            } else {
                break;
            }
            
        }
        startingCords = tempCoordinates;
        int i = 0;
        while( ! this.checkFree(startingCords)) {
            //if we are checking left
            
            if(startingCords.getXCoordinate().ordinal() <= 14){
                iterator.put(startingCords, this.getTile(tempCoordinates));
                startingCords.setXCoordinate(startingCords.getXCoordinate().ordinal() + 1);
                i++;
            }
            else{
                break;
            }
            
        }
        return iterator;
    }

    private HashMap<Coordinates, Tile> upDownIterator(Coordinates startingCords){
        HashMap<Coordinates, Tile> iterator = new HashMap<>();
        Coordinates tempCoordinates = null;
        while( ! this.checkFree(startingCords)) {
            //if we are checking left
            tempCoordinates = startingCords;
            if(startingCords.getXCoordinate().ordinal() >= 1){
                startingCords.setXCoordinate(startingCords.getXCoordinate().ordinal() + 1);
            } else {
                break;
            }
            
        }
        startingCords = tempCoordinates;
        int i = 0;
        while( ! this.checkFree(startingCords)) {
            //if we are checking left
            
            if(startingCords.getXCoordinate().ordinal() <= 14){
                iterator.put(startingCords, this.getTile(tempCoordinates));
                startingCords.setXCoordinate(startingCords.getXCoordinate().ordinal() + 1);
                i++;
            }
            else{
                break;
            }
            
        }
        return iterator;
    }


    /**
     * Enum which is used for encapsulation of a message which needs to be displaid.
     */
    public enum WordPlacementStatus{
        VALID, INVALID;
        private String errorMessage = "";
        private int score = 0;
        public void setErrorMessage(String message){
            errorMessage = message;
        }
        public String getErrorMessage(){
            return errorMessage;
        }
        public void setScore(int points){
            score = points;
        }
        public int getScore(){
            return score;
        }
    }
    
    public WordPlacementStatus checkPlacement(Coordinates coords, String word, String direction, boolean test, Player p){
        isTouching = false;
        WordPlacementStatus status;
        if (direction.equals("right")){
            status = check1Right(coords, word, test, p);
        }
        else if (direction.equals("down")){
            status = check1Down(coords, word, test, p);
        }
        else{
            status = WordPlacementStatus.INVALID;
            status.setErrorMessage("Direction broke somewhere");
            status.setScore(0);
            return status;
        }

        if()

    }
    private WordPlacementStatus check1Down(Coordinates coords, String word, boolean test, Player p) {
        HashMap<Coordinates, Tile> iterator = rightLeftIterator(coords);
        ArrayList<Tile> tilesTaken = new ArrayList<>();
        Board tempBoard = this;
        WordPlacementStatus status = WordPlacementStatus.INVALID;
        for (Coordinates c: iterator.keySet()){
            if(this.checkFree(c) && p.hasLetter(iterator.get(c).getLetter())){
                tilesTaken.add(iterator.get(c));
                this.placeTile(c,p.removeLetter(iterator.get(c).getLetter()));
            }
            else if(this.checkFree(c) && ! p.hasLetter(iterator.get(c).getLetter())){
                status.setErrorMessage(word);
                p.addLettersToHand(tilesTaken);
                //returnBoardToPreviousState
                return status;
            }
            else if(! this.checkFree(c) && Character.toUpperCase(iterator.get(c).getLetter()) != tempBoard.getLetter(c)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("word mismatch");
                return status;
            }
            
            if( ! check2Right(c)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("Invalid Placement - Horizontal Mismatch");
                return status;
            }
        }
    }
    private WordPlacementStatus check1Right(Coordinates coords, String word, boolean test, Player p) {
        HashMap<Coordinates, Tile> iterator = upDownIterator(coords);
        ArrayList<Tile> tilesTaken = new ArrayList<>();
        Board tempBoard = this;
        WordPlacementStatus status = WordPlacementStatus.INVALID;
        for (Coordinates c: iterator.keySet()){
            if(this.checkFree(c) && p.hasLetter(iterator.get(c).getLetter())){
                tilesTaken.add(iterator.get(c));
                this.placeTile(c,p.removeLetter(iterator.get(c).getLetter()));
            }
            else if(this.checkFree(c) && ! p.hasLetter(iterator.get(c).getLetter())){
                status.setErrorMessage(word);
                p.addLettersToHand(tilesTaken);
                //returnBoardToPreviousState
                return status;
            }
            else if(! this.checkFree(c) && Character.toUpperCase(iterator.get(c).getLetter()) != tempBoard.getLetter(c)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("word mismatch");
                return status;
            }
            
            if( ! check2Down(c)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("Invalid Placement - Vertical Mismatch");
                return status;
            }
        }
    }

    private boolean check2Right(Coordinates startingCoordinates){
        HashMap<Coordinates, Tile> iterator = rightLeftIterator(startingCoordinates);
        int i = 0;
        for(Coordinates c: iterator.keySet()){
            Character tempChar = word.charAt(i);
            if( ! tempChar.equals(iterator.get(c).getLetter())){
                return false;
            }
        }
        return true;
    }

    private boolean check2Down(Coordinates startingCoordinates) {
        HashMap<Coordinates, Tile> iterator = upDownIterator(startingCoordinates);
        int i = 0;
        String temp = "";
        for(Coordinates c: iterator.keySet()){
            temp = temp + iterator.get(c).getLetter();
        }
        
        if (temp.length() > 1){
            isTouching = true;
        }

        return true;
    }


    
}
