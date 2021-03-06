package position.test;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import model.piece.utils.Position;

class PositionTest {

    @Test
    void test() {
        //final Position pos = new Position(1, 2);
        final Position pos1 = Position.createNumericPosition(1, 1);
        final String a = "ciao";
        final Position pos = Position.createNewPosition("a8");
        final Position pos2 = Position.createNewPosition("e4");
        System.out.println(pos.getX() + " " +  pos.getY());
        System.out.println(pos2.getX() + " " + pos2.getY());
        System.out.println(pos1.getX() + " " + pos1.getY());
        //assertFalse(pos1.equals(pos));
        assertNotEquals(pos1, a);
    }

}
