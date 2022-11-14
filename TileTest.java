import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    Tile tile;
    @BeforeEach
    void setUp() {
        this.tile = new Tile();
    }

    @Test
    void getLetter() {
        assertEquals('.',tile.getLetter());
    }

    @Test
    void getScore() {
        assertEquals(0,tile.getScore());
    }

    @Test
    void setLetter() {
        tile.setLetter('t');
        assertEquals('t',tile.getLetter());
    }

    @Test
    void charToTile() {

    }
}