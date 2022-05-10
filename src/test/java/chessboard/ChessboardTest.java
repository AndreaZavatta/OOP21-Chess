package chessboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void creationChessboard() {
        final Chessboard board = chessboardFct.createNormalCB();
        assertEquals(32, board.getAllPieces().size());
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getColor().equals(Color.WHITE)).count());
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getColor().equals(Color.BLACK)).count());


    }

    @Test
    void tryIllegalMove() throws PositionNotFoundException {
        final Chessboard board = chessboardFct.createNormalCB();

        assertTrue(board.getPieceOnPosition(new Position(6, 0)).isPresent());
        assertFalse(board.getPieceOnPosition(new Position(4, 0)).isPresent());
        board.move(new Position(6, 0), new Position(4, 0));
        assertFalse(board.getPieceOnPosition(new Position(6, 0)).isPresent());
        assertTrue(board.getPieceOnPosition(new Position(4, 0)).isPresent());

        assertThrows(PositionNotFoundException.class, 
                () -> board.move(new Position(4, 0), new Position(2, 0)));
    }

    @Test
    void checkIfKill() throws PositionNotFoundException {
        final List<Piece> pieces = List.of(pieceFct.createPiece(Name.ROOK, new Position(0, 0), Color.BLACK),
                                            pieceFct.createPiece(Name.KING, new Position(1, 1), Color.BLACK),
                                            pieceFct.createPiece(Name.ROOK, new Position(7, 0), Color.WHITE),
                                            pieceFct.createPiece(Name.KING, new Position(7, 7), Color.WHITE));
        final Chessboard board = chessboardFct.createTestCB(pieces);

        board.move(new Position(7, 0), new Position(0, 0));
        assertEquals(board.getPieceOnPosition(new Position(0, 0)).get(), 
                    pieceFct.createPiece(Name.ROOK, new Position(0, 0), Color.WHITE));
        assertFalse(board.getAllPieces().contains(
                    pieceFct.createPiece(Name.ROOK, new Position(0, 0), Color.BLACK)));
    }

}
