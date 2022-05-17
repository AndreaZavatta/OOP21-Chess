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
        final List<Position> l = List.of(new Position(1, 0), new Position(2, 0), new Position(3, 0), 
                new Position(4, 0), new Position(5, 0), new Position(6, 0), new Position(7, 0), 
                new Position(0, 1), new Position(0, 2), new Position(0, 3), new Position(0, 4), 
                new Position(0, 5), new Position(0, 6), new Position(0, 7));
        final Piece rook = factory.createPiece(Name.ROOK, new Position(0, 0), Side.WHITE);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testCanEatAllPositionsRook() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(1, 0), new Position(0, 1));
        final Piece rook = factory.createPiece(Name.ROOK, new Position(0, 0), Side.WHITE);
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(1, 0), Side.BLACK);
        final Piece pawn1 = factory.createPiece(Name.PAWN, new Position(0, 1), Side.BLACK);
        list.add(pawn1);
        list.add(pawn);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testNoPosition() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece rook = factory.createPiece(Name.ROOK, new Position(0, 0), Side.WHITE);
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(1, 0), Side.WHITE);
        final Piece pawn1 = factory.createPiece(Name.PAWN, new Position(0, 1), Side.WHITE);
        list.add(pawn1);
        list.add(pawn);
        list.add(rook);
        assertEquals(l, rook.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testOnNormalBoardInit() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece rook = list.stream().filter(x -> x.getPosition().equals(new Position(0, 0)) 
                && x.getName().equals(Name.ROOK)).findFirst().get();
        final List<Position> l = List.of();
        assertEquals(l, rook.getAllPossiblePositions(chessboard));
    }
}
