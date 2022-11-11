import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    Coordinates coordinates;

    @BeforeEach
    void setUp() {
        this.coordinates = new Coordinates(Coordinates.xCoordinate.A, Coordinates.yCoordinate.EIGHT);
    }

    @Test
    void getXCoordinate() {

        assertEquals(Coordinates.xCoordinate.A,coordinates.getXCoordinate());
    }

    @Test
    void getYCoordinate() {
        assertEquals(Coordinates.yCoordinate.EIGHT,coordinates.getYCoordinate());
    }
}