import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterBagTest {
    LetterBag letterBag;

    @BeforeEach
    void setUp() {
        this.letterBag = new LetterBag();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRandomLetters() {
        assertEquals(5, letterBag.getRandomLetters(5).size());
    }

    @Test
    void getLetters() {
        assertEquals(98,letterBag.getLetters().size());
    }

    @Test
    void isEmpty() {
        assertEquals(false,letterBag.isEmpty());
    }
}