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
        final List<Piece> pieces = List.of(pieceFct.createPiece(Name.ROOK, Position.createNewPosition("a8"), Side.BLACK),
                                            pieceFct.createPiece(Name.KING, Position.createNewPosition("b7"), Side.BLACK),
                                            pieceFct.createPiece(Name.ROOK, Position.createNewPosition("h8"), Side.WHITE),
                                            pieceFct.createPiece(Name.KING, Position.createNewPosition("h1"), Side.WHITE));
        final Chessboard board = chessboardFct.createTestCB(pieces);

        board.move(Position.createNewPosition("h8"), Position.createNewPosition("a8"));
        assertEquals(board.getPieceOnPosition(Position.createNewPosition("a8")).get(), 
                    pieceFct.createPiece(Name.ROOK, Position.createNewPosition("a8"), Side.WHITE));
        assertFalse(board.getAllPieces().contains(
                    pieceFct.createPiece(Name.ROOK, Position.createNewPosition("a8"), Side.BLACK)));
    }

    @Test
    void checkBoardBorders() {
        final List<Piece> pieces = List.of(pieceFct.createPiece(Name.ROOK, Position.createNewPosition("a8"), Side.BLACK),
                                            pieceFct.createPiece(Name.KING, Position.createNewPosition("b7"), Side.BLACK),
                                            pieceFct.createPiece(Name.ROOK, Position.createNewPosition("a1"), Side.WHITE),
                                            pieceFct.createPiece(Name.KING, Position.createNewPosition("h1"), Side.WHITE),
                                            pieceFct.createPiece(Name.PAWN, Position.createNewPosition("f8"), Side.WHITE));
        final Chessboard board = chessboardFct.createTestCB(pieces);

        assertFalse(board.getPieceOnPosition(Position.createNewPosition("f8")).get()
                .getAllPossiblePositions(board).contains(Position.createNumericPosition(5, -1)));

        assertFalse(board.getPieceOnPosition(Position.createNewPosition("a8")).get()
                .getAllPossiblePositions(board).contains(Position.createNumericPosition(0, 8)));
    }

    @Test
    void tryRepetiveMoves() {
        final Chessboard board = chessboardFct.createNormalCB();
        board.move(Position.createNewPosition("g2"), 
                Position.createNewPosition("g4"));

        board.move(Position.createNewPosition("e7"),
                Position.createNewPosition("e5"));

        board.move(Position.createNewPosition("f2"), 
                Position.createNewPosition("f4"));

        board.move(Position.createNewPosition("d8"),
                Position.createNewPosition("e7"));

        board.move(Position.createNewPosition("e7"),
                Position.createNewPosition("h4"));

        assertTrue(board.getPieceOnPosition(Position.createNewPosition("g4")).get().getAllPossiblePositions(board)
                        .contains(Position.createNewPosition("g5")));
        assertFalse(board.getAllPosition(board.getPieceOnPosition(Position.createNewPosition("g4")).get())
                        .contains(Position.createNewPosition("g5")));
        assertTrue(board.getPieceOnPosition(Position.createNewPosition("h4")).get().getAllPossiblePositions(board)
                        .contains(Position.createNewPosition("e1")));
        assertTrue(board.getAllPosition(board.getPieceOnPosition(Position.createNewPosition("h4")).get())
                        .contains(Position.createNewPosition("e1")));
    }
}
