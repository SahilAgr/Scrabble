import java.util.ArrayList;
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


        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[x][y] = new Tile(".", 0,"1");
            }
        }
        /*
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


         */
        firstTurn = true;
        dict = new Dictionary();
        letterBag = new LetterBag();
        
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

        tempCoordinates.setXCoordinate(tempCoordinates.getXCoordinate().ordinal() + 1);
        while( ! this.checkFree(tempCoordinates)) {
            iterator.add(new Coordinates(tempCoordinates.getXCoordinate(), tempCoordinates.getYCoordinate()));
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
        
        tempCoordinates.setYCoordinate(tempCoordinates.getYCoordinate().ordinal() + 1);

        while( ! this.checkFree(tempCoordinates)) {
            if(tempCoordinates.getYCoordinate().ordinal() <= 14){
                iterator.add(new Coordinates(tempCoordinates.getXCoordinate(), tempCoordinates.getYCoordinate()));
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
    
    
    public Placement checkPlacement(Coordinates coords, String word, String direction, boolean test, Player p){
        isTouching = false;
        Placement place;
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        direction = direction.toLowerCase();
        if (direction.equals("right")){
            place = placeRight(tempCord, word, test, p);
        }
        else if (direction.equals("down")){
            place = placeDown(tempCord, word, test, p);
        }
        else{
            place = new Placement(false, "Direction broke somewhere??", 0);
            this.undoTurn();
            return place;
        }
        if (! place.isLegalPlace()){
            this.undoTurn();
            return place;
        }

        boolean finalCheck = false;
        if(! firstTurn){
            tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
            for(int i = 0; i < word.length(); i++){
                if (direction.equals("right")){
                    if(! checkDown(coords)){
                        place = new Placement(false, "Invalid Placement - Vertial Invalid Word" , 0);
                        this.undoTurn();
                        return place;
                    }
                    tempCord.setXCoordinate(tempCord.getXCoordinate().ordinal() + 1);
                    
                }
                if (direction.equals("down")){
                    if (! checkRight(tempCord)){
                        place = new Placement(false, "Invalid Placement - Horizontal Invalid Word" , 0);
                        this.undoTurn();
                        return place;
                    }
                    tempCord.setYCoordinate(tempCord.getYCoordinate().ordinal() + 1);
                }
            }
            if(! isTouching){
                p.addLettersToHand(tilesTaken);
                place = new Placement(false, "Floating Word." , 0);
                this.undoTurn();
                return place;
            }
            finalCheck = true;
        } else{
            boolean crossesStart = false;
            tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
            for(int i = 0; i < word.length(); i++){
                if (direction.equals("right")){
                    if ((tempCord.getXCoordinate().ordinal() == 7) && tempCord.getYCoordinate().ordinal() == 7){
                        crossesStart = true;
                    }
                    tempCord.setXCoordinate(tempCord.getXCoordinate().ordinal() + 1);
                }
                else if (direction.equals("down")){
                    if (tempCord.getXCoordinate().ordinal() == 7 && tempCord.getYCoordinate().ordinal() == 7){
                        crossesStart = true;
                    }
                    tempCord.setYCoordinate(tempCord.getYCoordinate().ordinal() + 1);
                }
            }
            if(! crossesStart){
                place = new Placement(false, "The placed word must cross start (H 08)." , 0);
                this.undoTurn();
                return place;
            }
            firstTurn = false;
            finalCheck = true;
        }

        if(finalCheck){
            
            p.addLettersToHand(letterBag.getRandomLetters(tilesTaken.size()));
            if(direction.equals("down")){
                int score = this.scoringInitialDown(coords);
                p.addScore(score);
                place = new Placement(true, "No errors", score);
            }
            if(direction.equals("right")){
                int score = this.scoringInitialRight(coords);
                p.addScore(score);
                place = new Placement(true, "No errors", score);
            }
        }
        if(test){
            undoTurn();
        } else { confirmTurn();
        }

        return place;


    }
    private Placement placeDown(Coordinates coords, String word, boolean test, Player p) {
        System.out.println(word);
        tilesTaken = new ArrayList<>();
        word = word.toUpperCase();
        Placement place;
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        if (word.length() == 1){
            place = new Placement(false, "Invalid Entry, words must be longer then 1.", 0);
            this.undoTurn();
            return place;
        }
        for(int i = 0; i < word.length(); i++) {
            if(this.checkFree(tempCord) && p.hasLetter(String.valueOf(word.charAt(i)))){
                this.placeTile(tempCord, new Tile(String.valueOf(word.charAt(i))));
                p.removeLetter(String.valueOf(word.charAt(i)));
                tilesTaken.add(this.getTile(tempCord));
                this.getTile(tempCord).setNewTile(true);
            } else if(this.checkFree(tempCord) && ! p.hasLetter(String.valueOf(word.charAt(i)))){
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "You dont have letter " + word.charAt(i), 0);
                return place;
            } else if(! this.checkFree(tempCord) && ! String.valueOf(word.charAt(i)).toUpperCase().equals(this.getLetter(tempCord))){
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "Word mismatch." + word.charAt(i), 0);
                return place;
            } else if(!this.checkFree(tempCord) && String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                isTouching = true;
            }
            if( ! checkRight(coords)){
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "Invalid Placement - Horizontal Mismatch" + word.charAt(i), 0);
                return place;
            }
            
            tempCord = new Coordinates(tempCord.getXCoordinate(), (tempCord.getYCoordinate().ordinal() + 1));
            
            
        }
        place = new Placement(true, word, 0);
        return place;
    }
    private Placement placeRight(Coordinates coords, String word, boolean test, Player p) {
        System.out.println(word);
        tilesTaken = new ArrayList<>();
        Placement place;
        word = word.toUpperCase();
        if (word.length() == 1){
            place = new Placement(false, "Invalid Entry, words must be longer then 1.", 0);
            return place;
        }
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        for(int i = 0; i < word.length();) {
            if(this.checkFree(tempCord) && p.hasLetter(String.valueOf(word.charAt(i)))){
                this.placeTile(tempCord, new Tile(String.valueOf(word.charAt(i))));
                p.removeLetter(String.valueOf(word.charAt(i)));
                tilesTaken.add(this.getTile(tempCord));
                this.getTile(tempCord).setNewTile(true);
            } else if(this.checkFree(tempCord) && ! p.hasLetter(String.valueOf(word.charAt(i)))){
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "You dont have letter " + word.charAt(i), 0);
                return place;
            } else if(! this.checkFree(tempCord) && ! String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "Word mismatch." + word.charAt(i), 0);
                return place;
            } else if(!this.checkFree(tempCord) && String.valueOf(word.charAt(i)).equals(this.getLetter(tempCord))){
                isTouching = true;
            }
            if( ! checkDown(tempCord)){
                p.addLettersToHand(tilesTaken);
                p.addLettersToHand(tilesTaken);
                this.undoTurn();
                place = new Placement(false, "Invalid Placement - Vertical Mismatch" + word.charAt(i), 0);
                return place;
            }
            
            tempCord = new Coordinates(tempCord.getXCoordinate().ordinal() + 1, tempCord.getYCoordinate());
            i++;
            
        }
        place = new Placement(true, word, 0);
        return place;
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
                if(! checkFree(new Coordinates(coords.getXCoordinate(), coords.getYCoordinate().ordinal() + 1)) || ! checkFree(new Coordinates(coords.getXCoordinate(),  coords.getYCoordinate().ordinal() - 1))){
                    otherWordsScore += this.scoringSecondaryDown(coords);
                }
                /*if(this.getTile(c).getMultiplier().equals("2w")){
                    multi *= 2;
                } else if(this.getTile(c).getMultiplier().equals("3w")){
                    multi *= 3;
                }*/
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
                if(! checkFree(new Coordinates(coords.getXCoordinate().ordinal() + 1, coords.getYCoordinate())) || ! checkFree(new Coordinates(coords.getXCoordinate().ordinal() - 1, coords.getYCoordinate()))){
                    otherWordsScore += this.scoringSecondaryRight(coords);
                }
                /*if(this.getTile(c).getMultiplier().equals("2w")){
                    multi *= 2;
                } else if(this.getTile(c).getMultiplier().equals("3w")){
                    multi *= 3;
                }*/
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
                /*if(this.getTile(c).getMultiplier().equals("2w")){
                    multi *= 2;
                } else if(this.getTile(c).getMultiplier().equals("3w")){
                    multi *= 3;
                }*/

            }
        }
        System.out.println(score * multi);
        return score * multi;
    }

    private int scoringSecondaryDown(Coordinates coords){
        List<Coordinates> iterator = upDownIterator(coords);
        int score = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            score += this.getTile(c).getScore();
            if (this.getTile(c).getNewTile()){
                /*if(this.getTile(c).getMultiplier().equals("2w")){
                    multi *= 2;
                } else if(this.getTile(c).getMultiplier().equals("3w")){
                    multi *= 3;
                }*/

            }
        }
        System.out.println(score * multi);
        return score * multi;
    }

    private void undoTurn(){
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                if (gameBoard[y][x].getNewTile()){
                    gameBoard[y][x].resetTile();
                }
            }
        }
    }

    private void confirmTurn(){
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                if (gameBoard[y][x].getNewTile()){
                    gameBoard[y][x].setNewTile(false);
                }
            }
        }
    }

    public static void main(String[] args){
        Board bord = new Board();
        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("a"));
        hand.add(new Tile("e"));
        hand.add(new Tile("a"));
        p.addLettersToHand(hand);
        bord.placeDown(new Coordinates(7, 7), "tea", false, p);

        
    }
    
}
