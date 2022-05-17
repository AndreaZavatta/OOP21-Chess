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

class QueenTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testQueenAllPositionsClearBoard() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(5, 5), new Position(6, 6), new Position(7, 7),
                new Position(3, 3), new Position(2, 2), new Position(1, 1), new Position(0, 0), 
                new Position(5, 3), new Position(6, 2), new Position(7, 1), new Position(3, 5), 
                new Position(2, 6), new Position(1, 7), new Position(5, 4), new Position(6, 4), 
                new Position(7, 4), new Position(3, 4), new Position(2, 4), new Position(1, 4), 
                new Position(0, 4), new Position(4, 5), new Position(4, 6), new Position(4, 7), 
                new Position(4, 3), new Position(4, 2), new Position(4, 1), new Position(4, 0));
        final Piece queen = factory.createPiece(Name.QUEEN, new Position(4, 4), Side.WHITE);
        list.add(queen);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testQueenCantMove() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece queen = factory.createPiece(Name.QUEEN, new Position(0, 0), Side.BLACK);
        final Piece queen1 = factory.createPiece(Name.QUEEN, new Position(1, 1), Side.BLACK);
        final Piece queen2 = factory.createPiece(Name.QUEEN, new Position(1, 0), Side.BLACK);
        final Piece queen3 = factory.createPiece(Name.QUEEN, new Position(0, 1), Side.BLACK);
        list.add(queen);
        list.add(queen3);
        list.add(queen2);
        list.add(queen1);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testQueenCanMoveOnlyEat() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(1, 1), new Position(1, 0), new Position(0, 1));
        final Piece queen = factory.createPiece(Name.QUEEN, new Position(0, 0), Side.BLACK);
        final Piece queen1 = factory.createPiece(Name.QUEEN, new Position(1, 1), Side.WHITE);
        final Piece queen2 = factory.createPiece(Name.QUEEN, new Position(1, 0), Side.WHITE);
        final Piece queen3 = factory.createPiece(Name.QUEEN, new Position(0, 1), Side.WHITE);
        list.add(queen);
        list.add(queen3);
        list.add(queen2);
        list.add(queen1);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testQueenOnRealBoardStart() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece queen = list.stream().filter(x -> x.getPosition().equals(new Position(3, 0)) 
                && x.getName().equals(Name.QUEEN)).findFirst().get();
        final List<Position> l = List.of();
        assertEquals(l, queen.getAllPossiblePositions(chessboard));
    }
}
