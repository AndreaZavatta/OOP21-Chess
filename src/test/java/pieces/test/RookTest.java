package pieces.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import piece.utils.Name;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class RookTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testRookAllPosition() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(1, 0),
                Position.createNumericPosition(2, 0),
                Position.createNumericPosition(3, 0), 
                Position.createNumericPosition(4, 0),
                Position.createNumericPosition(5, 0),
                Position.createNumericPosition(6, 0),
                Position.createNumericPosition(7, 0), 
                Position.createNumericPosition(0, 1),
                Position.createNumericPosition(0, 2),
                Position.createNumericPosition(0, 3),
                Position.createNumericPosition(0, 4), 
                Position.createNumericPosition(0, 5),
                Position.createNumericPosition(0, 6),
                Position.createNumericPosition(0, 7));
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(0, 0), Side.WHITE);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testCanEatAllPositionsRook() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(1, 0),
                Position.createNumericPosition(0, 1));
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(0, 0), Side.WHITE);
        final Piece pawn = factory.createPiece(Name.PAWN, Position.createNumericPosition(1, 0), Side.BLACK);
        final Piece pawn1 = factory.createPiece(Name.PAWN, Position.createNumericPosition(0, 1), Side.BLACK);
        list.add(pawn1);
        list.add(pawn);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testNoPosition() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNumericPosition(0, 0), Side.WHITE);
        final Piece pawn = factory.createPiece(Name.PAWN, Position.createNumericPosition(1, 0), Side.WHITE);
        final Piece pawn1 = factory.createPiece(Name.PAWN, Position.createNumericPosition(0, 1), Side.WHITE);
        list.add(pawn1);
        list.add(pawn);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testOnNormalBoardInit() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece rook = list.stream().filter(x -> x.getPosition().equals(Position.createNumericPosition(0, 0)) 
                && x.getName().equals(Name.ROOK)).findFirst().get();
        final List<Position> l = List.of();
        assertEquals(l, rook.getAllPossiblePositions(chessboard));
    }
}
