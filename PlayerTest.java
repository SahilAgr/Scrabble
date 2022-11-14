import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Tile tile;

    @BeforeEach
    void setUp() {
        this.player = new Player("Name");
        this.tile = new Tile();
    }

    @Test
    void addScore() {
        player.addScore(10);
        assertEquals(10,player.getScore());
    }

    @Test
    void getScore() {
        assertEquals(0,player.getScore());
    }

    @Test
    void getName() {
        assertEquals("Name",player.getName());
    }

    @Test
    void getHand() {
        assertEquals(0,player.getHand().size());
    }

    @Test
    void addLettersToHand() {
        ArrayList<Tile> testTile = new ArrayList<>();
        testTile.add(Tile.charToTile('c'));
        player.addLettersToHand(testTile);
        assertEquals(1,player.getHand().size());
    }

    @Test
    void removeLetters() {
        ArrayList<Tile> testTile = new ArrayList<>();
        testTile.add(Tile.charToTile('c'));
        testTile.add(Tile.charToTile('d'));
        player.addLettersToHand(testTile);
        player.removeLetters(testTile);
        assertEquals(0,player.getHand().size());

    }

    @Test
    void removeLetter() {
        ArrayList<Tile> testTile = new ArrayList<>();
        testTile.add(Tile.charToTile('c'));
        testTile.add(Tile.charToTile('d'));
        player.addLettersToHand(testTile);
        player.removeLetter('c');
        assertEquals(1,player.getHand().size());
    }

    @Test
    void hasLetter() {
        ArrayList<Tile> testTile = new ArrayList<>();
        testTile.add(Tile.charToTile('c'));
        testTile.add(Tile.charToTile('d'));
        player.addLettersToHand(testTile);
        assertEquals(true,player.hasLetter('d'));
    }
}