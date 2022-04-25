package position.test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import piece.utils.Position;

class PositionTest {

    @Test
    void test() {
        final Position pos = new Position(1, 2);
        final Position pos1 = new Position(1, 1);
        final String a = "ciao";

        assertFalse(pos1.equals(pos));
        assertNotEquals(pos1, a);
    }

}
