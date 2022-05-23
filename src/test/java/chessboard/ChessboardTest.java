package chessboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import piece.utils.Side;
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
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getColor().equals(Side.WHITE)).count());
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getColor().equals(Side.BLACK)).count());
    }

    @Test
    void checkIfKill() {
        final List<Piece> pieces = List.of(pieceFct.createPiece(Name.ROOK, new Position(0, 0), Side.BLACK),
                                            pieceFct.createPiece(Name.KING, new Position(1, 1), Side.BLACK),
                                            pieceFct.createPiece(Name.ROOK, new Position(0, 7), Side.WHITE),
                                            pieceFct.createPiece(Name.KING, new Position(7, 7), Side.WHITE));
        final Chessboard board = chessboardFct.createTestCB(pieces);

        board.move(new Position(0, 7), new Position(0, 0));
        assertEquals(board.getPieceOnPosition(new Position(0, 0)).get(), 
                    pieceFct.createPiece(Name.ROOK, new Position(0, 0), Side.WHITE));
        assertFalse(board.getAllPieces().contains(
                    pieceFct.createPiece(Name.ROOK, new Position(0, 0), Side.BLACK)));
    }

    @Test
    void checkBoardBorders() {
        final List<Piece> pieces = List.of(pieceFct.createPiece(Name.ROOK, new Position(0, 0), Side.BLACK),
                                            pieceFct.createPiece(Name.KING, new Position(1, 1), Side.BLACK),
                                            pieceFct.createPiece(Name.ROOK, new Position(0, 7), Side.WHITE),
                                            pieceFct.createPiece(Name.KING, new Position(7, 7), Side.WHITE),
                                            pieceFct.createPiece(Name.PAWN, new Position(5, 0), Side.WHITE));
        final Chessboard board = chessboardFct.createTestCB(pieces);

        assertFalse(board.getPieceOnPosition(new Position(5, 0)).get()
                .getAllPossiblePositions(board).contains(new Position(5, -1)));

        assertFalse(board.getPieceOnPosition(new Position(0, 0)).get()
                .getAllPossiblePositions(board).contains(new Position(0, 8)));
    }

    @Test
    void tryRepetiveMoves() {
        final Chessboard board = chessboardFct.createNormalCB();
        board.move(new Position(6, 6), 
                    new Position(6, 4));

        board.move(new Position(4, 1),
                    new Position(4, 3));

        board.move(new Position(5, 6), 
                new Position(5, 4));

        board.move(new Position(3, 0),
                new Position(4, 1));

        board.move(new Position(4, 1),
                    new Position(7, 4));

        assertTrue(board.getPieceOnPosition(new Position(6, 4)).get().getAllPossiblePositions(board).contains(new Position(6, 3)));
        assertTrue(board.getAllPosition(board.getPieceOnPosition(new Position(6, 4)).get()).contains(new Position(6, 3)));
        assertTrue(board.getPieceOnPosition(new Position(7, 4)).get().getAllPossiblePositions(board).contains(new Position(4, 7)));
        assertTrue(board.getAllPosition(board.getPieceOnPosition(new Position(7, 4)).get()).contains(new Position(4, 0)));
    }
}
