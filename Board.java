
import java.util.ArrayList;
import java.util.List;


/**
 * The Board Class that creates the board in the terminal
 * The Board is made up of Tiles
 * @authors Patrick Ma, Matthew Huitema, Sahil Agrawal
 */
public class Board {

    private Tile[][] gameBoard = new Tile[15][15];
    private boolean isTouching;
    private boolean firstTurn;
    private Dictionary dict;
    private ArrayList<Tile> tilesTaken;
    private LetterBag letterBag;

    private final String THREE_TIMES_WORD = "3W";
    private final String TWO_TIMES_WORD = "2W";
    private final String THREE_TIMES_LETTER = "3L";
    private final String TWO_TIMES_LETTER = "2L";


    /**
     * The constructor for the Board class
     *
     */
    public Board() {


        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[x][y] = new Tile(".");
            }
        }
        
        gameBoard[7][7].setLetter("+");

        gameBoard[0][7].setLetter(THREE_TIMES_WORD);
        gameBoard[7][0].setLetter(THREE_TIMES_WORD);
        gameBoard[14][0].setLetter(THREE_TIMES_WORD);
        gameBoard[0][14].setLetter(THREE_TIMES_WORD);
        gameBoard[14][7].setLetter(THREE_TIMES_WORD);
        gameBoard[7][14].setLetter(THREE_TIMES_WORD);
        gameBoard[14][14].setLetter(THREE_TIMES_WORD);
        gameBoard[0][0].setLetter(THREE_TIMES_WORD);

        gameBoard[1][1].setLetter(TWO_TIMES_WORD);
        gameBoard[2][2].setLetter(TWO_TIMES_WORD);
        gameBoard[3][3].setLetter(TWO_TIMES_WORD);
        gameBoard[4][4].setLetter(TWO_TIMES_WORD);
        gameBoard[10][10].setLetter(TWO_TIMES_WORD);
        gameBoard[11][11].setLetter(TWO_TIMES_WORD);
        gameBoard[12][12].setLetter(TWO_TIMES_WORD);
        gameBoard[13][13].setLetter(TWO_TIMES_WORD);
        gameBoard[1][13].setLetter(TWO_TIMES_WORD);
        gameBoard[2][12].setLetter(TWO_TIMES_WORD);
        gameBoard[3][11].setLetter(TWO_TIMES_WORD);
        gameBoard[4][10].setLetter(TWO_TIMES_WORD);
        gameBoard[10][4].setLetter(TWO_TIMES_WORD);
        gameBoard[11][3].setLetter(TWO_TIMES_WORD);
        gameBoard[12][2].setLetter(TWO_TIMES_WORD);
        gameBoard[13][1].setLetter(TWO_TIMES_WORD);

        gameBoard[0][3].setLetter(TWO_TIMES_LETTER);
        gameBoard[0][11].setLetter(TWO_TIMES_LETTER);
        gameBoard[3][0].setLetter(TWO_TIMES_LETTER);
        gameBoard[11][0].setLetter(TWO_TIMES_LETTER);
        gameBoard[3][7].setLetter(TWO_TIMES_LETTER);
        gameBoard[7][3].setLetter(TWO_TIMES_LETTER);
        gameBoard[2][8].setLetter(TWO_TIMES_LETTER);
        gameBoard[8][2].setLetter(TWO_TIMES_LETTER);
        gameBoard[2][6].setLetter(TWO_TIMES_LETTER);
        gameBoard[3][14].setLetter(TWO_TIMES_LETTER);
        gameBoard[14][3].setLetter(TWO_TIMES_LETTER);
        gameBoard[6][6].setLetter(TWO_TIMES_LETTER);
        gameBoard[6][8].setLetter(TWO_TIMES_LETTER);
        gameBoard[8][6].setLetter(TWO_TIMES_LETTER);
        gameBoard[8][8].setLetter(TWO_TIMES_LETTER);
        gameBoard[6][2].setLetter(TWO_TIMES_LETTER);
        gameBoard[14][11].setLetter(TWO_TIMES_LETTER);
        gameBoard[11][14].setLetter(TWO_TIMES_LETTER);
        gameBoard[11][7].setLetter(TWO_TIMES_LETTER);
        gameBoard[7][11].setLetter(TWO_TIMES_LETTER);
        gameBoard[12][6].setLetter(TWO_TIMES_LETTER);
        gameBoard[6][12].setLetter(TWO_TIMES_LETTER);
        gameBoard[12][8].setLetter(TWO_TIMES_LETTER);
        gameBoard[8][12].setLetter(TWO_TIMES_LETTER);

        gameBoard[1][5].setLetter(THREE_TIMES_LETTER);
        gameBoard[5][1].setLetter(THREE_TIMES_LETTER);
        gameBoard[1][9].setLetter(THREE_TIMES_LETTER);
        gameBoard[9][1].setLetter(THREE_TIMES_LETTER);
        gameBoard[5][5].setLetter(THREE_TIMES_LETTER);
        gameBoard[5][9].setLetter(THREE_TIMES_LETTER);
        gameBoard[9][5].setLetter(THREE_TIMES_LETTER);
        gameBoard[9][9].setLetter(THREE_TIMES_LETTER);
        gameBoard[13][5].setLetter(THREE_TIMES_LETTER);
        gameBoard[5][13].setLetter(THREE_TIMES_LETTER);
        gameBoard[13][9].setLetter(THREE_TIMES_LETTER);
        gameBoard[9][13].setLetter(THREE_TIMES_LETTER);

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
            gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].setLetter(tile.getString());
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
        if(! dict.isLegalWord(word)){
            return new Placement(false,"Not a Valid Word", 0);
        }
        
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
        }
        if (! place.isLegalPlace()){
            return place;
        }

        boolean finalCheck = false;
        if(! firstTurn){
            tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
            if(! isTouching){
                return this.invalidPlacement("Invalid Placement - Floating Word.", p);
            }
            for(int i = 0; i < word.length(); i++){
                if (direction.equals("right")){
                    if(! checkDown(coords)){
                        return this.invalidPlacement("Invalid Placement - Vertical Invalid Word", p);
                    }
                    tempCord.setXCoordinate(tempCord.getXCoordinate().ordinal() + 1);
                    
                }
                if (direction.equals("down")){
                    if (! checkRight(tempCord)){
                        return this.invalidPlacement("Invalid Placement - Horizontal Invalid Word", p);
                    }
                    tempCord.setYCoordinate(tempCord.getYCoordinate().ordinal() + 1);
                }
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
                return this.invalidPlacement("The placed word must cross start (H 08).", p);
            }

            finalCheck = true;
        }
        int score = 0;
        if(finalCheck){
            if(direction.equals("down")){
                if(! this.checkDown(coords)){
                    return invalidPlacement("Vertical Mismatch. Try placing right instead?", p);
                }
                score = this.scoringInitialDown(coords);
                place = new Placement(true, "Placement Successful! You got: ", score);
            }
            if(direction.equals("right")){
                if(! this.checkRight(coords)){
                    return invalidPlacement("Horizontal Mismatch. Try placing down instead?", p);
                }
                score = this.scoringInitialRight(coords);
                place = new Placement(true, "Placement Successful! You got: ", score);
            }
        }
        if(test){
            undoTurn();
            p.addLettersToHand(tilesTaken);
            place.setErrorMessage("You would've got: ");
        } else {
            firstTurn = false;
            confirmTurn();
            p.addScore(score);
            p.addLettersToHand(letterBag.getRandomLetters(tilesTaken.size()));
        }
        return place;


    }
    public Placement placeDown(Coordinates coords, String word, boolean test, Player p) {
        tilesTaken = new ArrayList<>();
        word = word.toUpperCase();
        Placement place;
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        if (word.length() == 1){
            return invalidPlacement("Word must be longer than 1 letter.", p);
        }
        for(int i = 0; i < word.length(); i++) {
            place = check1LetterPlacement(tempCord, word.charAt(i), p);
            if (! place.isLegalPlace()){
                return place;
            }
            if( ! checkRight(tempCord)){
                return invalidPlacement("Invalid Placement - Horizontal Word Mismatch", p);
            }
            if(tempCord.getYCoordinate().ordinal() == 14){
                if (i < word.length()){
                    return invalidPlacement("You went out of bounds! Your word is too long.", p);
    
                }
            }
            tempCord = new Coordinates(tempCord.getXCoordinate(), tempCord.getYCoordinate().ordinal() + 1);
            
        }
        place = new Placement(true, word, 0);
        return place;
    }
    public Placement placeRight(Coordinates coords, String word, boolean test, Player p) {
        tilesTaken = new ArrayList<>();
        Placement place;
        word = word.toUpperCase();
        if (word.length() == 1){
            place = new Placement(false, "Invalid Entry, words must be longer then 1.", 0);
            return place;
        }
        Coordinates tempCord = new Coordinates(coords.getXCoordinate(), coords.getYCoordinate());
        
        for(int i = 0; i < word.length(); i++) {
            place = check1LetterPlacement(tempCord, word.charAt(i), p);
            if (! place.isLegalPlace()){
                return place;
            }
            if( ! checkDown(tempCord)){
                return invalidPlacement("Invalid Placement - Vertical Word Mismatch", p);
            }
            if(tempCord.getYCoordinate().ordinal() == 14){
                if (i < word.length()){
                    return invalidPlacement("You went out of bounds! Your word is too long.", p);
                }
            }
            tempCord = new Coordinates(tempCord.getXCoordinate().ordinal() + 1, tempCord.getYCoordinate());
        }
        place = new Placement(true, word, 0);
        this.printBoard();
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
        if(dict.isLegalWord(temp) || temp.length() == 1){
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
        if(dict.isLegalWord(temp) || temp.length() == 1){
            return true;
        }
        return false;
    }

    public int scoringInitialRight(Coordinates coords){
        List<Coordinates> iterator = rightLeftIterator(coords);
        int score = 0;
        int otherWordsScore = 0;
        int multi = 1;
        
        for(Coordinates c: iterator){
            if(this.getTile(c).getNewTile()){
                if(! checkFree(new Coordinates(c.getXCoordinate(), c.getYCoordinate().ordinal() + 1)) || ! checkFree(new Coordinates(c.getXCoordinate(), coords.getYCoordinate().ordinal() - 1))){
                    otherWordsScore += this.scoringSecondaryDown(c);
                }
                multi = multi * this.getTile(c).getMulti();
            }
            score += this.getTile(c).getScore();
        }
        System.out.println(multi);
        score = (score * multi) + otherWordsScore;
        return score;
    }

    public int scoringInitialDown(Coordinates coords){
        List<Coordinates> iterator = upDownIterator(coords);
        int score = 0;
        int otherWordsScore = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            if(this.getTile(c).getNewTile()){
                if(! checkFree(new Coordinates(c.getXCoordinate().ordinal() + 1, c.getYCoordinate())) || ! checkFree(new Coordinates(c.getXCoordinate().ordinal() - 1, c.getYCoordinate()))){                    
                    otherWordsScore += this.scoringSecondaryRight(c);
                }
                multi = multi * this.getTile(c).getMulti();
            }
            score += this.getTile(c).getScore();
        }
        System.out.println(multi);
        score = (score * multi) + otherWordsScore;
        return score;
    }
    public int scoringSecondaryRight(Coordinates coords) {
        List<Coordinates> iterator = rightLeftIterator(coords);
        int score = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            score += this.getTile(c).getScore();
            if (this.getTile(c).getNewTile()){
                
                multi = multi * this.getTile(c).getMulti();

            }
        }
        score = score * multi;
        return score;
    }

    public int scoringSecondaryDown(Coordinates coords){
        List<Coordinates> iterator = upDownIterator(coords);
        int score = 0;
        int multi = 1;
        for(Coordinates c: iterator){
            score += this.getTile(c).getScore();
            if (this.getTile(c).getNewTile()){
                multi = multi * this.getTile(c).getMulti();

            }
        }
        score = score * multi;
        return score;
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
    private void giveTilesBack(Player p){
        ArrayList<Tile> hand = new ArrayList<>();
        for(Tile t: tilesTaken){
            hand.add(new Tile(t.getString()));
        }
        p.addLettersToHand(hand);
    }

    private Placement check1LetterPlacement(Coordinates coords, char c, Player p){
        if(this.checkFree(coords) && p.hasLetter(String.valueOf(c))){
            this.placeTile(coords, new Tile(String.valueOf(c)));
            p.removeLetter(String.valueOf(c));
            tilesTaken.add(new Tile (this.getTile(coords).getString()));
            this.getTile(coords).setNewTile(true);
            return new Placement(true, "Something went wrong, you shouldnt see this", 0);

        } else if(this.checkFree(coords) && ! p.hasLetter(String.valueOf(c))){
            if(p.hasLetter("*")){
                p.removeLetter("*");
                this.placeTile(coords, new Tile(Character.toString(c), 0, true));
                tilesTaken.add(new Tile("*"));
            }
            else{
                return invalidPlacement("You dont have letter " + c, p);
            }

        } else if(! this.checkFree(coords) && ! String.valueOf(c).equals(this.getLetter(coords))){
            return this.invalidPlacement("Word mismatch at " + c, p);

        } else if(!this.checkFree(coords) && String.valueOf(c).equals(this.getLetter(coords))){
            isTouching = true;
            return new Placement(true, "Something went wrong, you shouldnt see this", 0);

        }
        return new Placement(true, "", 0);
    }

    private Placement invalidPlacement(String errorMessage, Player p){
        this.undoTurn();
        this.giveTilesBack(p);
        this.confirmTurn();
        return new Placement(false, errorMessage, 0);
    }

    public static void main(String[] args){
        Board bord = new Board();
        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        bord.printBoard();
        Placement pl = bord.checkPlacement(new Coordinates(7, 7), "test","right", false, p);
        System.out.println(pl.getErrorMessage());
        System.out.println(pl.getScore());
        bord.printBoard();
        pl = bord.checkPlacement(new Coordinates(11, 3), "tests","down", false, p);
        System.out.println(pl.getErrorMessage());
        System.out.println(pl.getScore());
        bord.printBoard();
        pl = bord.checkPlacement(new Coordinates(7, 7), "test","down", false, p);
        System.out.println(pl.getErrorMessage());
        System.out.println(pl.getScore());
        bord.printBoard();
        bord.placeTile(new Coordinates(7, 7), new Tile("t"));
        System.out.println( bord.checkRight(new Coordinates(7, 7)));
        bord.placeTile(new Coordinates(8, 7), new Tile("e"));
        System.out.println(bord.checkDown(new Coordinates(8, 7)));

        
    }
    
}
