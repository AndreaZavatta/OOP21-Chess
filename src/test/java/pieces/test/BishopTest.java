package pieces.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.Numbers;
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
        final List<Position> l = List.of(Position.createNumericPosition(5, 6),
                Position.createNumericPosition(6, 7),
                Position.createNumericPosition(3, 4),
                Position.createNumericPosition(2, 3),
                Position.createNumericPosition(1, 2),
                Position.createNumericPosition(0, 1),
                Position.createNumericPosition(5, 4), 
                Position.createNumericPosition(6, 3),
                Position.createNumericPosition(7, 2),
                Position.createNumericPosition(3, 6),
                Position.createNumericPosition(2, 7));
        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(4, 5), Side.WHITE);
        list.add(bishop);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testWithAlliesAndNoPositionAvailable() {
        final List<Piece> list = new ArrayList<>();
        //        final List<Position> l = List.of(new Position(5, 6), new Position(6, 7), new Position(3, 4),
        //                new Position(2, 3), new Position(1, 2), new Position(0, 1), new Position(5, 4), 
        //                new Position(6, 3), new Position(7, 2), new Position(3, 6), new Position(2, 7));

        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(4, 5), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(5, 6), Side.WHITE);
        final Piece rook1 = factory.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), Side.WHITE);
        final Piece rook2 = factory.createPiece(Name.ROOK, Position.createNumericPosition(5, 4), Side.WHITE);
        final Piece rook3 = factory.createPiece(Name.ROOK, Position.createNumericPosition(3, 6), Side.WHITE);
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
        final List<Position> l = List.of(Position.createNumericPosition(5, 6),
                Position.createNumericPosition(3, 4),
                Position.createNumericPosition(5, 4),
                Position.createNumericPosition(3, 6));

        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(4, 5), Side.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(6, 7), Side.WHITE);
        final Piece rook1 = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 7), Side.WHITE);
        final Piece rook2 = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 3), Side.WHITE);
        final Piece rook3 = factory.createPiece(Name.ROOK, Position.createNumericPosition(6, 3), Side.WHITE);
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
        final List<Position> l = List.of(Position.createNumericPosition(1, 1));

        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(0, 0), Side.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 2), Side.WHITE);
        list.add(bishop);
        list.add(rook);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testWithEnemy() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(1, 1),
                Position.createNumericPosition(2, 2));
        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(0, 0), Side.WHITE);

        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 2), Side.BLACK);
        list.add(bishop);
        list.add(rook);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void test2WithEnemy() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(2, 2),
                Position.createNumericPosition(0, 0), 
                Position.createNumericPosition(2, 0),
                Position.createNumericPosition(0, 2));
        final Piece bishop = factory.createPiece(Name.BISHOP, Position.createNumericPosition(1, 1), Side.BLACK);

        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(0, 0), Side.WHITE);
        final Piece rook1 = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 2), Side.WHITE);
        final Piece rook2 = factory.createPiece(Name.ROOK, Position.createNumericPosition(2, 0), Side.WHITE);
        final Piece rook3 = factory.createPiece(Name.ROOK, Position.createNumericPosition(0, 2), Side.WHITE);
        list.add(bishop);
        list.add(rook3);
        list.add(rook2);
        list.add(rook1);
        list.add(rook);
        assertEquals(l, bishop.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testBlackOnNormalBoard() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece bishop = list.stream().filter(x -> x.getPosition().equals(Position.createNumericPosition(2, 0)) 
                && x.getName().equals(Name.BISHOP)).findFirst().get();
        final List<Position> l = List.of();
        assertEquals(l, bishop.getAllPossiblePositions(chessboard));
    }

}
