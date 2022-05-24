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
        final List<Position> l = List.of(Position.createNumericPosition(5, 5), Position.createNumericPosition(6, 6), Position.createNumericPosition(7, 7),
                Position.createNumericPosition(3, 3), Position.createNumericPosition(2, 2), Position.createNumericPosition(1, 1), Position.createNumericPosition(0, 0), 
                Position.createNumericPosition(5, 3), Position.createNumericPosition(6, 2), Position.createNumericPosition(7, 1), Position.createNumericPosition(3, 5), 
                Position.createNumericPosition(2, 6), Position.createNumericPosition(1, 7), Position.createNumericPosition(5, 4), Position.createNumericPosition(6, 4), 
                Position.createNumericPosition(7, 4), Position.createNumericPosition(3, 4), Position.createNumericPosition(2, 4), Position.createNumericPosition(1, 4), 
                Position.createNumericPosition(0, 4), Position.createNumericPosition(4, 5), Position.createNumericPosition(4, 6), Position.createNumericPosition(4, 7), 
                Position.createNumericPosition(4, 3), Position.createNumericPosition(4, 2), Position.createNumericPosition(4, 1), Position.createNumericPosition(4, 0));
        final Piece queen = factory.createPiece(Name.QUEEN, Position.createNumericPosition(4, 4), Side.WHITE);
        list.add(queen);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testQueenCantMove() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece queen = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 0), Side.BLACK);
        final Piece queen1 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(1, 1), Side.BLACK);
        final Piece queen2 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(1, 0), Side.BLACK);
        final Piece queen3 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 1), Side.BLACK);
        list.add(queen);
        list.add(queen3);
        list.add(queen2);
        list.add(queen1);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testQueenCanMoveOnlyEat() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(1, 1), Position.createNumericPosition(1, 0), Position.createNumericPosition(0, 1));
        final Piece queen = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 0), Side.BLACK);
        final Piece queen1 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(1, 1), Side.WHITE);
        final Piece queen2 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(1, 0), Side.WHITE);
        final Piece queen3 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 1), Side.WHITE);
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
        final Piece queen = list.stream().filter(x -> x.getPosition().equals(Position.createNumericPosition(3, 0)) 
                && x.getName().equals(Name.QUEEN)).findFirst().get();
        final List<Position> l = List.of();
        assertEquals(l, queen.getAllPossiblePositions(chessboard));
    }

    @Test
    void testQueenMove() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNumericPosition(1, 1), Position.createNumericPosition(1, 0), Position.createNumericPosition(2, 0), Position.createNumericPosition(0, 1));
        final List<Position> l1 = List.of(Position.createNumericPosition(2, 1), Position.createNumericPosition(0, 1), Position.createNumericPosition(2, 0), Position.createNumericPosition(0, 0), Position.createNumericPosition(1, 1));

        final Piece queen = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 0), Side.BLACK);
        final Piece queen1 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(1, 1), Side.WHITE);
        final Piece queen2 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(2, 0), Side.WHITE);
        final Piece queen3 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(0, 1), Side.WHITE);
        final Piece queen4 = factory.createPiece(Name.QUEEN, Position.createNumericPosition(2, 1), Side.WHITE);

        list.add(queen);
        list.add(queen3);
        list.add(queen2);
        list.add(queen1);
        list.add(queen4);
        assertEquals(l, queen.getAllPossiblePositions(board.createTestCB(list)));
        queen.setPosition(Position.createNumericPosition(1, 0));
        assertEquals(l1, queen.getAllPossiblePositions(board.createTestCB(list)));
    }
}
