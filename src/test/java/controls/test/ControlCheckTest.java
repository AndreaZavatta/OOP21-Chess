package controls.test;
import static piece.utils.Name.KING;
import static piece.utils.Name.BISHOP;
import static piece.utils.Name.ROOK;
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
    @Test
    void testIsInCheck() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 0), WHITE);
        Piece whiteKing = pieceFactory.createPiece(KING, new Position(5, 4), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(control.isInCheck(chessboard, BLACK));
        assertFalse(control.isInCheck(chessboard, WHITE));
    }
    @Test
    void testPinOnKing() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(3, 0), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 0), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        assertFalse(blackBishop.canMove(new Position(4, 1), chessboard));
        assertFalse(blackBishop.canMove(new Position(2, 1), chessboard));
        assertFalse(blackBishop.canMove(new Position(1, 2), chessboard));
    }
    @Test
    void testClassicalMove() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(3, 0), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        assertTrue(blackBishop.canMove(new Position(4, 1), chessboard));
        assertTrue(blackBishop.canMove(new Position(2, 1), chessboard));
        assertTrue(blackBishop.canMove(new Position(1, 2), chessboard));
    }
    @Test
    void testCoverageFromCheck() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackRook = pieceFactory.createPiece(ROOK, new Position(4, 1), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        assertTrue(blackKing.canMove(new Position(1, 1), chessboard));
        assertTrue(blackKing.canMove(new Position(1, 0), chessboard));
        assertFalse(blackKing.canMove(new Position(0, 1), chessboard));
    }
    @Test
    void testEscapeFromCheck() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackRook = pieceFactory.createPiece(ROOK, new Position(4, 1), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        assertTrue(blackRook.canMove(new Position(0, 1), chessboard));
        assertFalse(blackRook.canMove(new Position(2, 1), chessboard));
        assertFalse(blackRook.canMove(new Position(5, 1), chessboard));
    }
}
