package pieces.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class BishopTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testNormalMovement() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(5, 6), new Position(6, 7), new Position(3, 4),
                new Position(2, 3), new Position(1, 2), new Position(0, 1), new Position(5, 4), 
                new Position(6, 3), new Position(7, 2), new Position(3, 6), new Position(2, 7));
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 5), Color.WHITE);
        list.add(bishop);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testWithAlliesAndNoPositionAvailable() {
        final List<Piece> list = new ArrayList<>();
        //        final List<Position> l = List.of(new Position(5, 6), new Position(6, 7), new Position(3, 4),
        //                new Position(2, 3), new Position(1, 2), new Position(0, 1), new Position(5, 4), 
        //                new Position(6, 3), new Position(7, 2), new Position(3, 6), new Position(2, 7));

        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 5), Color.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, new Position(5, 6), Color.WHITE);
        final Piece rook1 = factory.createPiece(Name.ROOK, new Position(3, 4), Color.WHITE);
        final Piece rook2 = factory.createPiece(Name.ROOK, new Position(5, 4), Color.WHITE);
        final Piece rook3 = factory.createPiece(Name.ROOK, new Position(3, 6), Color.WHITE);
        list.add(bishop);
        list.add(rook3);
        list.add(rook2);
        list.add(rook1);
        list.add(rook);

        assertEquals(List.of(), bishop.getAllPossiblePositions(board.createTestCB(list)));
        //        assertNotEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
        assertTrue(bishop.getAllPossiblePositions(board.createTestCB(list)).isEmpty());
    }

    @Test
    void testWithAlliesAndPositionAvailable() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(5, 6), new Position(3, 4),
                new Position(5, 4), new Position(3, 6));

        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 5), Color.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, new Position(6, 7), Color.WHITE);
        final Piece rook1 = factory.createPiece(Name.ROOK, new Position(2, 7), Color.WHITE);
        final Piece rook2 = factory.createPiece(Name.ROOK, new Position(2, 3), Color.WHITE);
        final Piece rook3 = factory.createPiece(Name.ROOK, new Position(6, 3), Color.WHITE);
        list.add(bishop);
        list.add(rook3);
        list.add(rook2);
        list.add(rook1);
        list.add(rook);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void test2WithAlliesAndPositionAvailable() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(1, 1));

        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(0, 0), Color.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, new Position(2, 2), Color.WHITE);
        list.add(bishop);
        list.add(rook);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

}
