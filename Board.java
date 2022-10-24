import java.util.ArrayList;

public class Board {
    Tile[][] gameBoard = new Tile[15][15];

    public Board() {
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                gameBoard[x][y] = new Tile();
            }
        }
        gameBoard[7][7].letter = '⛝';
    }

    private void placeTile(Coordinates coords, Tile tile){
        if (gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()].letter == '⬜'){
            gameBoard[coords.getXCoordinate().ordinal()][coords.getYCoordinate().ordinal()] = tile;
        }else{
            System.out.println("Tile cannot be placed here");
        }
    }

    public void printBoard(){
        for(int x=0 ; x<15 ; x++){
            for (int y=0 ; y<15 ; y++) {
                System.out.print(gameBoard[x][y].letter+" ");
            }
            System.out.println();
        }System.out.println();
    }
    public static void main(String[] args) {
        Board board = new Board();
        Tile Atile = new Tile('A',1);
        Coordinates coordinates = new Coordinates(Coordinates.xCoordinate.F, Coordinates.yCoordinate.NINE);
        board.printBoard();
        board.placeTile(coordinates,Atile);
        board.printBoard();
    }

    
}
