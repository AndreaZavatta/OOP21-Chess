package controls.test;
import static piece.utils.Name.KING;
import static piece.utils.Name.BISHOP;
import static piece.utils.Name.ROOK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static piece.utils.Color.BLACK;
import static piece.utils.Color.WHITE;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import controls.ControlCheck;
import controls.ControlCheckImpl;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class ControlCheckTest {
    private Chessboard chessboard;
    private static ChessboardFactory chessboardFactory;
    private static PieceFactory pieceFactory;
    private static ControlCheck control;
    @BeforeAll
    public static void init() {
        chessboardFactory = new ChessboardFactoryImpl();
        pieceFactory = new PieceFactoryImpl();
        control = new ControlCheckImpl();
    }
    void testCanEatKingWithoutKing() {
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(5, 1), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(whiteRook));
        assertFalse(control.isInCheck(chessboard, WHITE));
    }
    @Test
    void beInCheckEnemyAttackKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(1, 1), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(5, 1), WHITE);
        final Piece whiteKing = pieceFactory.createPiece(KING, new Position(6, 5), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(control.isInCheck(chessboard, BLACK));
        assertFalse(control.isInCheck(chessboard, WHITE));
    }
    @Test
    void ableToMovePinOnKingFalse() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(1, 1), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(4, 1), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(5, 1), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackBishop);
        assertEquals(pos, List.of());
    }
    @Test
    void ableToMoveNoPinOnKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(1, 1), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(4, 1), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(5, 3), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackBishop);
        assertTrue(pos.contains(new Position(5, 2)));
        assertTrue(pos.contains(new Position(3, 2)));
        assertTrue(pos.contains(new Position(2, 3)));
    }
    @Test
    void ableToMoveOnlyToCoverCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(1, 1), BLACK);
        final Piece blackRook = pieceFactory.createPiece(ROOK, new Position(5, 2), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(1, 3), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackRook);
        assertEquals(pos, List.of(new Position(1, 2)));
    }
    @Test
    void ableToMoveOnlyToEscapeFromCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(1, 1), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(1, 3), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackKing);
        assertFalse(pos.contains(new Position(1, 2)));
        assertTrue(pos.contains(new Position(2, 1)));
        assertTrue(pos.contains(new Position(2, 2)));
    }
}
