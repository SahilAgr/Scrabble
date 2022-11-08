import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    Dictionary dictionary;

    @BeforeEach
    void setUp(){
        this.dictionary = new Dictionary();
    }
    @AfterEach
    void tearDown(){
    }
    @org.junit.jupiter.api.Test
    void isLegalWordTest() {
        assertEquals(true,dictionary.isLegalWord("test"));

    }
    @org.junit.jupiter.api.Test
    void isNotLegalWordTest(){
        assertEquals(false, dictionary.isLegalWord("abolity"));
    }

}