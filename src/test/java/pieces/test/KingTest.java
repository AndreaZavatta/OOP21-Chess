package pieces.test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import org.junit.jupiter.api.Test;
import piece.utils.Name;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

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
}
