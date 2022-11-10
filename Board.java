import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Board Class that creates the board in the terminal
 * The Board is made up of Tiles
 * @author Patrick Ma
 */
public class Board {

    private Tile[][] gameBoard = new Tile[15][15];//in future use nested hashmap
    private boolean isTouching;
    private boolean firstTurn;
    private Dictionary dict;
    private ArrayList<Tile> tilesTaken;
    private LetterBag letterBag;

    /**
     * The constructor for the Board class
     *
     */
    public Board() {

        letterBag = new LetterBag();

        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[x][y] = new Tile(".", 0,"1");
            }
        }
        gameBoard[7][7].setLetter("+");

        gameBoard[0][7].setLetter("3w");
        gameBoard[7][0].setLetter("3w");
        gameBoard[14][0].setLetter("3w");
        gameBoard[0][14].setLetter("3w");
        gameBoard[14][7].setLetter("3w");
        gameBoard[7][14].setLetter("3w");
        gameBoard[14][14].setLetter("3w");
        gameBoard[0][0].setLetter("3w");

        gameBoard[1][1].setLetter("2w");
        gameBoard[2][2].setLetter("2w");
        gameBoard[3][3].setLetter("2w");
        gameBoard[4][4].setLetter("2w");
        gameBoard[10][10].setLetter("2w");
        gameBoard[11][11].setLetter("2w");
        gameBoard[12][12].setLetter("2w");
        gameBoard[13][13].setLetter("2w");
        gameBoard[1][13].setLetter("2w");
        gameBoard[2][12].setLetter("2w");
        gameBoard[3][11].setLetter("2w");
        gameBoard[4][10].setLetter("2w");
        gameBoard[10][4].setLetter("2w");
        gameBoard[11][3].setLetter("2w");
        gameBoard[12][2].setLetter("2w");
        gameBoard[13][1].setLetter("2w");

        gameBoard[0][3].setLetter("2l");
        gameBoard[0][11].setLetter("2l");
        gameBoard[3][0].setLetter("2l");
        gameBoard[11][0].setLetter("2l");
        gameBoard[3][7].setLetter("2l");
        gameBoard[7][3].setLetter("2l");
        gameBoard[2][8].setLetter("2l");
        gameBoard[8][2].setLetter("2l");
        gameBoard[2][6].setLetter("2l");
        gameBoard[3][14].setLetter("2l");
        gameBoard[14][3].setLetter("2l");
        gameBoard[6][6].setLetter("2l");
        gameBoard[6][8].setLetter("2l");
        gameBoard[8][6].setLetter("2l");
        gameBoard[8][8].setLetter("2l");
        gameBoard[6][2].setLetter("2l");
        gameBoard[14][11].setLetter("2l");
        gameBoard[11][14].setLetter("2l");
        gameBoard[11][7].setLetter("2l");
        gameBoard[7][11].setLetter("2l");
        gameBoard[12][6].setLetter("2l");
        gameBoard[6][12].setLetter("2l");
        gameBoard[12][8].setLetter("2l");
        gameBoard[8][12].setLetter("2l");

        gameBoard[1][5].setLetter("3l");
        gameBoard[5][1].setLetter("3l");
        gameBoard[1][9].setLetter("3l");
        gameBoard[9][1].setLetter("3l");
        gameBoard[5][5].setLetter("3l");
        gameBoard[5][9].setLetter("3l");
        gameBoard[9][5].setLetter("3l");
        gameBoard[9][9].setLetter("3l");
        gameBoard[13][5].setLetter("3l");
        gameBoard[5][13].setLetter("3l");
        gameBoard[13][9].setLetter("3l");
        gameBoard[9][13].setLetter("3l");

        firstTurn = true;
        dict = new Dictionary();
        
    }
    public Board(Tile[][] tileArray){
        gameBoard = tileArray;
        dict = new Dictionary();
    }

    /**
     * Returns the letter at a specific tile coordinate
     * @param coords Coordinate
     * @return char
     */
    public String getLetter(Coordinates coords) {
        return gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].getString();
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
        String test = this.getLetter(coords);
        return (test.equals(".") || test.equals("+") || test.equals("2W") || test.equals("3W") || test.equals("2L") || test.equals("3L"));
    }

    /**
     * Places a letter on a tile
     * @param coords Coordinates
     * @param tile Tile
     * 
     * @return boolean
     */
    public boolean placeTile(Coordinates coords, Tile tile){
        if (checkFree(coords)){
            gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()] = tile;
            return true;
        }else{
            return false;
        }
    }

    public Tile[][] getGameBoard(){
        return gameBoard;
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
                if(gameBoard[x][y].getString().length() == 1){
                    System.out.print(gameBoard[y][x].getString()+"  ");
                }
                else{
                    System.out.print(gameBoard[y][x].getString()+" "); 
                }
            }
            System.out.println();
        }System.out.println();
    }
    private List<Coordinates> rightLeftIterator(Coordinates startingCords){
        List<Coordinates> iterator = new ArrayList<>();
        Coordinates tempCoordinates = new Coordinates(startingCords.getXCoordinate(), startingCords.getYCoordinate());
        while( ! this.checkFree(tempCoordinates)) {
            
            if(tempCoordinates.getXCoordinate().ordinal() >= 1){
                tempCoordinates.setXCoordinate(tempCoordinates.getXCoordinate().ordinal() - 1);
            } else {
                break;
            }
            
        }
        while( ! this.checkFree(tempCoordinates)) {
            iterator.add(tempCoordinates);
            if(tempCoordinates.getXCoordinate().ordinal() <= 14){
                tempCoordinates.setXCoordinate(tempCoordinates.getXCoordinate().ordinal() + 1);
            }
            else{
                break;
            }
            
        }
        return iterator;
    }

    private List<Coordinates> upDownIterator(Coordinates startingCords){
        List<Coordinates> iterator = new ArrayList<>();
        Coordinates tempCoordinates = new Coordinates(startingCords.getXCoordinate(), startingCords.getYCoordinate());
        while( ! this.checkFree(tempCoordinates)) {
            if(tempCoordinates.getYCoordinate().ordinal() >= 1){
                tempCoordinates.setYCoordinate(tempCoordinates.getYCoordinate().ordinal() - 1);
            } else {
                break;
            }
            
        }
        
        while( ! this.checkFree(tempCoordinates)) {
            if(tempCoordinates.getYCoordinate().ordinal() <= 14){
                iterator.add(tempCoordinates);
                tempCoordinates.setYCoordinate(tempCoordinates.getYCoordinate().ordinal() + 1);
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
        public static WordPlacementStatus ordToPlacementStatus(int ord){
            return WordPlacementStatus.values()[ord];
        }
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
        WordPlacementStatus status = WordPlacementStatus.INVALID;
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        if (direction.equals("right")){
            status = placeRight(tempCord, word, test, p);
            System.out.println("check 1");
            System.out.println(status);
        }
        else if (direction.equals("down")){
            status = placeDown(tempCord, word, test, p);
            System.out.println("check 1");
            System.out.println(status);
        }
        else{
            status = WordPlacementStatus.INVALID;
            status.setErrorMessage("Direction broke somewhere???");
            return status;
        }
        System.out.println("check 2");
        System.out.println(status);
        if (status == WordPlacementStatus.INVALID){
            return status;
        }
        tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        boolean finalCheck = false;
        if(! firstTurn){
            for(int i = 0; i < word.length(); i++){
                if (direction.equals("right")){
                    if(! checkDown(coords)){
                        status.setErrorMessage("Invalid Placement - Vertial Invalid Word");
                        status = WordPlacementStatus.INVALID;
                        return status;
                    }
                    tempCord.setXCoordinate(tempCord.getXCoordinate().ordinal() + 1);
                    
                }
                if (direction.equals("down")){
                    if (! checkRight(tempCord)){
                        status.setErrorMessage("Invalid Placement - Horizontal Invalid Word");
                        status = WordPlacementStatus.INVALID;
                        return status;
                    }
                    tempCord.setYCoordinate(tempCord.getYCoordinate().ordinal() + 1);
                }
            }
            if(! isTouching){
                status.setErrorMessage("Floating Word.");
                p.addLettersToHand(tilesTaken);
                status = WordPlacementStatus.INVALID;
                return status;
            }
            finalCheck = true;
        } else{
            boolean crossesStart = false;
            for(int i = 0; i < word.length(); i++){
                if (direction.equals("right")){
                    if (tempCord.getXCoordinate().ordinal() == 7 && tempCord.getXCoordinate().ordinal() == 7){
                        crossesStart = true;
                    }
                    tempCord.setXCoordinate(tempCord.getXCoordinate().ordinal() + 1);
                }
                else if (direction.equals("down")){
                    if (tempCord.getXCoordinate().ordinal() == 7 && tempCord.getXCoordinate().ordinal() == 7){
                        crossesStart = true;
                    }
                    tempCord.setYCoordinate(tempCord.getYCoordinate().ordinal() + 1);
                }
            }
            if(! crossesStart){
                status = WordPlacementStatus.INVALID;
                status.setErrorMessage("The placed word must cross start (H 08).");
                return status;
            }
            firstTurn = false;
            finalCheck = true;
        }

        if(finalCheck){
            
            status = WordPlacementStatus.VALID;
            for(Tile t: tilesTaken){
                System.out.print(t.getString()+ " ");
            }
            p.removeLetters(tilesTaken);
            if(direction.equals("down")){
                int score = this.scoringInitialDown(coords);
                p.addScore(score);
                status.setScore(score);
            }
            if(direction.equals("right")){
                int score = this.scoringInitialRight(coords);
                System.out.println(score);
                p.addScore(score);
                status.setScore(score);
            }
        }
        return status;


    }
    private WordPlacementStatus placeDown(Coordinates coords, String word, boolean test, Player p) {
        //HashMap<Coordinates, Tile> iterator = rightLeftIterator(coords);
        tilesTaken = new ArrayList<>();
        word = word.toUpperCase();
        WordPlacementStatus status = WordPlacementStatus.INVALID;
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        if (word.length() == 1){
            status.setErrorMessage("Invalid Entry, words must be longer then 1.");
            status = WordPlacementStatus.INVALID;
            return status;
        }
        for(int i = 0; i < word.length(); i++) {
            if(this.checkFree(tempCord) && p.hasLetter(String.valueOf(word.charAt(i)))){
                this.placeTile(tempCord, new Tile(String.valueOf(word.charAt(i))));
                p.removeLetter(String.valueOf(word.charAt(i)));
                tilesTaken.add(this.getTile(tempCord));
                this.getTile(tempCord).setNewTile(true);
            } else if(this.checkFree(tempCord) && ! p.hasLetter(String.valueOf(word.charAt(i)))){
                status.setErrorMessage("You dont have letter " + word.charAt(i));
                p.addLettersToHand(tilesTaken);
                //undoTurn()
                status = WordPlacementStatus.INVALID;
                return status;
            } else if(! this.checkFree(tempCord) && ! String.valueOf(word.charAt(i)).toUpperCase().equals(this.getLetter(tempCord))){
                status.setErrorMessage("Word mismatch.");
                p.addLettersToHand(tilesTaken);
                //undoTurn()
                status = WordPlacementStatus.INVALID;
                return status;
            } else if(!this.checkFree(tempCord) && String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                isTouching = true;
            }
            if( ! checkRight(coords)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("Invalid Placement - Horizontal Mismatch");
                //undoTurn()
                status = WordPlacementStatus.ordToPlacementStatus(1);
                return status;
            }
            
            tempCord = new Coordinates(tempCord.getXCoordinate(), (tempCord.getYCoordinate().ordinal() + 1));
            
            
        }
        status = WordPlacementStatus.VALID;
        return status;
    }
    private WordPlacementStatus placeRight(Coordinates coords, String word, boolean test, Player p) {
        tilesTaken = new ArrayList<>();
        WordPlacementStatus status = WordPlacementStatus.INVALID;
        word = word.toUpperCase();
        if (word.length() == 1){
            status.setErrorMessage("Invalid Entry, words must be longer then 1.");
            status = WordPlacementStatus.INVALID;
            return status;
        }
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        for(int i = 0; i < word.length();) {
            if(this.checkFree(tempCord) && p.hasLetter(String.valueOf(word.charAt(i)))){
                this.placeTile(tempCord, new Tile(String.valueOf(word.charAt(i))));
                p.removeLetter(String.valueOf(word.charAt(i)));
                tilesTaken.add(this.getTile(tempCord));
                this.getTile(tempCord).setNewTile(true);
            } else if(this.checkFree(tempCord) && ! p.hasLetter(String.valueOf(word.charAt(i)))){
                status.setErrorMessage("You dont have letter " + word.charAt(i));
                p.addLettersToHand(tilesTaken);
                //undoTurn()
                status = WordPlacementStatus.INVALID;
                return status;
            } else if(! this.checkFree(tempCord) && ! String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                status.setErrorMessage("Word mismatch.");
                p.addLettersToHand(tilesTaken);
                //undoTurn()
                status = WordPlacementStatus.INVALID;
                return status;
            } else if(!this.checkFree(tempCord) && String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                isTouching = true;
            }
            if( ! checkDown(tempCord)){
                p.addLettersToHand(tilesTaken);
                status.setErrorMessage("Invalid Placement - Horizontal Mismatch");
                //undoTurn()
                status = WordPlacementStatus.INVALID;
                return status;
            }
            
            tempCord = new Coordinates(tempCord.getXCoordinate().ordinal() + 1, tempCord.getYCoordinate());
            i++;
            
        }
        status = WordPlacementStatus.VALID;
        return status;
    }

    private boolean checkRight(Coordinates startingCoordinates){
        List<Coordinates> iterator = rightLeftIterator(startingCoordinates);
        String temp = "";
        for(Coordinates c: iterator){
            temp = temp + this.getLetter(c);
        }
        
        if (temp.length() > 1){
            isTouching = true;
        }
        else{
            return true;
        }
        if(dict.isLegalWord(temp) || temp.equals("")){
            return true;
        }
        return false;
    }

    private boolean checkDown(Coordinates startingCoordinates) {
        List<Coordinates> iterator = upDownIterator(startingCoordinates);
        String temp = "";
        for(Coordinates c: iterator){
            temp = temp + this.getLetter(c);
        }
        
        if (temp.length() > 1){
            isTouching = true;
        }

        if(dict.isLegalWord(temp) || temp.equals("")){
            return true;
        }
        return false;
    }

    private int scoringInitialRight(Coordinates coords){
        List<Coordinates> iterator = rightLeftIterator(coords);
        int score = 0;
        int otherWordsScore = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            if(this.getTile(c).getNewTile()){
                if(! checkFree(new Coordinates(coords.getXCoordinate().ordinal() + 1, coords.getYCoordinate())) || ! checkFree(new Coordinates(coords.getXCoordinate().ordinal() - 1, coords.getYCoordinate()))){
                    otherWordsScore += this.scoringSecondaryDown(coords);
                }
                switch(this.getTile(c).getMultiplier()){
                    case "2w": multi *= 2;
                    case "3w": multi *= 3;
                }
            }
            score += this.getTile(c).getScore();
        }
        return score * multi + otherWordsScore;
    }

    private int scoringInitialDown(Coordinates coords){
        List<Coordinates> iterator = upDownIterator(coords);
        int score = 0;
        int otherWordsScore = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            if(this.getTile(c).getNewTile()){
                if(! checkFree(new Coordinates(coords.getXCoordinate(), coords.getYCoordinate().ordinal() + 1)) || ! checkFree(new Coordinates(coords.getXCoordinate(), coords.getYCoordinate().ordinal() - 1))){
                    otherWordsScore += this.scoringSecondaryRight(coords);
                }
                switch(this.getTile(c).getMultiplier()){
                    case "2w": multi *= 2;
                    case "3w": multi *= 3;
                }
            }
            score += this.getTile(c).getScore();
        }
        return score * multi + otherWordsScore;
    }
    private int scoringSecondaryRight(Coordinates coords) {
        List<Coordinates> iterator = rightLeftIterator(coords);
        int score = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            score += this.getTile(c).getScore();
            if (this.getTile(c).getNewTile()){
                switch(this.getTile(c).getMultiplier()){
                    case "2w": multi *= 2;
                    case "3w": multi *= 3;
                }

            }
        }

        return score * multi;
    }

    private int scoringSecondaryDown(Coordinates coords){
        List<Coordinates> iterator = upDownIterator(coords);
        int score = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            score += this.getTile(c).getScore();
            if (this.getTile(c).getNewTile()){
                switch(this.getTile(c).getMultiplier()){
                    case "2w": multi *= 2;
                    case "3w": multi *= 3;
                }

            }
        }

        return score * multi;
    }

    public static void main(String[] args){
        Board bord = new Board();
        bord.placeTile(new Coordinates(7, 7), new Tile("t"));
        bord.placeTile(new Coordinates(8, 7), new Tile("e"));
        bord.placeTile(new Coordinates(9, 7), new Tile("a"));
        bord.printBoard();
        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        hand.add(new Tile("e"));
        hand.add(new Tile("a"));
        p.addLettersToHand(hand);
        System.out.println(bord.placeDown(new Coordinates(7, 8), "ea", false, p).getErrorMessage());
        bord.printBoard();
    }
    
}
