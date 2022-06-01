package pieces.test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import org.junit.jupiter.api.Test;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testCastlingPositionWhiteSideLeft() {
        final List<Piece> list = new ArrayList<>();
        final Piece king = factory.createPiece(Name.KING, Position.createNewPosition("e1"), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNewPosition("h1"), Side.WHITE);
        list.add(king);
        list.add(rook);
        assertTrue(king.getAllPossiblePositions(board.createTestCB(list)).contains(Position.createNewPosition("g1")));
        king.setPosition(king.getPosition());
        assertFalse(king.getAllPossiblePositions(board.createTestCB(list)).contains(Position.createNewPosition("g1")));
    }

    @Test
    void testNormalMoves() {
        final List<Piece> list = new ArrayList<>();
        final Piece king = factory.createPiece(Name.KING, Position.createNewPosition("e4"), Side.WHITE);
        final List<Position> positionsCanGo = List.of(Position.createNewPosition("f4"),
                Position.createNewPosition("d4"),
                Position.createNewPosition("e3"),
                Position.createNewPosition("e5"),
                Position.createNewPosition("f3"),
                Position.createNewPosition("d5"),
                Position.createNewPosition("f5"),
                Position.createNewPosition("d3"));
        list.add(king);
        assertEquals(positionsCanGo, king.getAllPossiblePositions(board.createTestCB(list)));
    }
}
