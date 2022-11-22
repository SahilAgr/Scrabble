import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the Test Class for Board.
 * For Milestone 3, Testing the method in Board class
 *
 * @author Anirudh Bakshi (101158699)
 * @version 1.0
 */
class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getLetter() {

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test","right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test","down", false, p);
        board.printBoard();
        String s = board.getLetter(new Coordinates(7, 7));
        System.out.println(s);
        assertEquals("T", s);

    }

    @Test
    void getTile() {

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test","right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test","down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        System.out.println(s.getString());
        assertEquals("T", s.getString());

    }

    @Test
    void checkFree() {

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test","right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test","down", false, p);
        board.printBoard();
        assertEquals(false, board.checkFree(new Coordinates(7, 7)));

    }

    @Test
    void placeTile() {

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test","right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test","down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals(false, board.placeTile(new Coordinates(7, 7), s));
        assertEquals(true, board.placeTile(new Coordinates(6, 7), s));

    }

    @Test
    void checkPlacement() {

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        System.out.println(board.checkPlacement(new Coordinates(7, 7), "TEST123", "DOWN", true, p).isLegalPlace());
        assertEquals(false, board.checkPlacement(new Coordinates(7, 7), "TEST123", "DOWN", true, p).isLegalPlace());
        assertEquals(4, board.checkPlacement(new Coordinates(7, 7), "TEST", "RIGHT", true, p).getScore());
        assertEquals("You would've got: ", board.checkPlacement(new Coordinates(7, 7), "TEST", "RIGHT", true, p).getErrorMessage());
        assertEquals("Placement Successful! You got: ", board.checkPlacement(new Coordinates(7, 7), "TEST", "RIGHT", false, p).getErrorMessage());
        assertEquals(4, board.checkPlacement(new Coordinates(7, 7), "TEST", "RIGHT", true, p).getScore());

    }


    @Test
    void placeDown(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals("Invalid Entry, words must be longer then 1.", board.placeDown(new Coordinates(7, 7), "T", false, p).getErrorMessage());
        assertEquals("You dont have letter T", board.placeDown(new Coordinates(7, 7), "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT", false, p).getErrorMessage());

    }

    @Test
    void placeRight(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals("Invalid Entry, words must be longer then 1.", board.placeRight(new Coordinates(7, 7), "T", false, p).getErrorMessage());
        assertEquals("Word mismatch at T", board.placeRight(new Coordinates(7, 7), "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT", false, p).getErrorMessage());

    }

    @Test
    void scoringInitialRight(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals(4, board.scoringInitialRight(new Coordinates(7, 7)));
        assertEquals(0, board.scoringInitialRight(new Coordinates(4, 3)));

    }

    @Test
    void scoringInitialDown(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals(1, board.scoringSecondaryDown(new Coordinates(7, 7)));
        assertEquals(0, board.scoringSecondaryDown(new Coordinates(4, 3)));

    }

    @Test
    void scoringSecondaryRight(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals(4, board.scoringSecondaryRight(new Coordinates(7, 7)));
        assertEquals(0, board.scoringSecondaryRight(new Coordinates(4, 3)));

    }

    @Test
    void scoringSecondaryDown(){

        Player p = new Player(null);
        ArrayList<Tile> hand = new ArrayList<>();
        board.printBoard();
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        hand.add(new Tile("t"));
        hand.add(new Tile("t"));
        hand.add(new Tile("e"));
        hand.add(new Tile("s"));
        p.addLettersToHand(hand);
        board.checkPlacement(new Coordinates(7, 7), "test", "right", false, p);
        board.checkPlacement(new Coordinates(10, 7), "test", "down", false, p);
        board.printBoard();
        Tile s = board.getTile(new Coordinates(7, 7));
        assertEquals(1, board.scoringSecondaryDown(new Coordinates(7, 7)));
        assertEquals(0, board.scoringSecondaryDown(new Coordinates(4, 3)));

    }

}
