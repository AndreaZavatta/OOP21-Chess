package controls.test;
import static piece.utils.Name.KING;
import static piece.utils.Name.BISHOP;
import static piece.utils.Name.ROOK;
import static piece.utils.Name.KNIGHT;
import static piece.utils.Name.QUEEN;
import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;
import static piece.utils.Position.createNewPosition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import board.ControlCheck;
import board.ControlCheckImpl;
import exceptions.KingNotFoundException;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class ControlCheckTest {
    private Chessboard chessboard;
    private static ChessboardFactory chessboardFactory = new ChessboardFactoryImpl();
    private static PieceFactory pieceFactory  = new PieceFactoryImpl();
    private static ControlCheck control  = new ControlCheckImpl();

    @Test
    void testKingNotFoundException() {
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("e8"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(whiteRook));
        assertThrows(
                KingNotFoundException.class,
                () -> control.isInCheck(chessboard, BLACK)
         );

    }

    @Test
    void beInCheckEnemyAttackKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("e8"), WHITE);
        final Piece whiteKing = pieceFactory.createPiece(KING, createNewPosition("f4"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(control.isInCheck(chessboard, BLACK));
        assertFalse(control.isInCheck(chessboard, WHITE));
    }
    @Test
    void ableToMovePinOnKingFalse() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, createNewPosition("d8"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("e8"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackBishop);
        assertEquals(pos, List.of());
    }
    @Test
    void ableToMoveNoPinOnKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, createNewPosition("d8"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("e6"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackBishop);
        assertTrue(pos.containsAll(List.of(createNewPosition("f6"), createNewPosition("e7"), createNewPosition("g5"), createNewPosition("h4"), createNewPosition("c7"), createNewPosition("b6"), createNewPosition("a5"))));
    }
    @Test
    void ableToMoveOnlyToCoverCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackRook = pieceFactory.createPiece(ROOK, createNewPosition("e7"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("a6"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackRook);
        assertEquals(pos, List.of(createNewPosition("a7")));
    }
    @Test
    void ableToMoveOnlyToEscapeFromCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("a6"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackKing);
        assertTrue(pos.containsAll(List.of(createNewPosition("b7"), createNewPosition("b8"))));
    }
    @Test
    void ableToMoveToCoverCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("c6"), BLACK);
        final Piece blackKinght = pieceFactory.createPiece(KNIGHT, createNewPosition("e3"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackKinght, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackKinght);
        assertEquals(pos, List.of(createNewPosition("c4")));
    }
    @Test
    void ableToMoveToCoverCheckOrToEatTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("c6"), BLACK);
        final Piece blackKinght = pieceFactory.createPiece(KNIGHT, createNewPosition("e4"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackKinght, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackKinght);
        assertTrue(pos.containsAll(List.of(createNewPosition("c5"), createNewPosition("c3"))));
    }
    @Test
    void ableToMoveOnlyVerticallyTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, createNewPosition("c7"), BLACK);
        final Piece blackQueen = pieceFactory.createPiece(QUEEN, createNewPosition("c5"), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackQueen, whiteRook));
        final List<Position> pos = control.controlledMoves(chessboard, blackQueen);
        assertTrue(pos.containsAll(List.of(createNewPosition("c6"), createNewPosition("c3"), createNewPosition("c4"))));
    }

}
