package chessboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import exceptions.PositionNotFoundException;
import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class ChessboardTest {

    private final PieceFactory pieceFct = new PieceFactoryImpl();
    private final ChessboardFactory chessboardFct = new ChessboardFactoryImpl();

    @Test
    void test() throws PositionNotFoundException {
        final Chessboard board = chessboardFct.createNormalCB();
        assertEquals(32, board.getAllPieces().size());
        assertTrue(board.getPieceOnPosition(new Position(6, 0)).isPresent());
        assertFalse(board.getPieceOnPosition(new Position(4, 0)).isPresent());
        board.move(new Position(6, 0), new Position(4, 0));
        assertFalse(board.getPieceOnPosition(new Position(6, 0)).isPresent());
        assertTrue(board.getPieceOnPosition(new Position(4, 0)).isPresent());
    }

}
