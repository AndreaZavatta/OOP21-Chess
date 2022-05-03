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
        List<Position> pos = control.removeMoveInCheck(chessboard, blackBishop, blackBishop.getAllPossiblePositions(chessboard));
        assertTrue(pos.contains(new Position(4, 1)));
        assertTrue(pos.contains(new Position(2, 1)));
        assertTrue(pos.contains(new Position(1, 2)));
    }
    @Test
    void testClassicalMove() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(3, 0), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        List<Position> pos = control.removeMoveInCheck(chessboard, blackBishop, blackBishop.getAllPossiblePositions(chessboard));
        assertTrue(pos.contains(new Position(4, 1)));
        assertTrue(pos.contains(new Position(2, 1)));
        assertTrue(pos.contains(new Position(1, 2)));
    }
    @Test
    void testCoverageFromCheck() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackRook = pieceFactory.createPiece(ROOK, new Position(4, 1), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        List<Position> pos = control.removeMoveInCheck(chessboard, blackKing, blackKing.getAllPossiblePositions(chessboard));
        assertTrue(pos.contains(new Position(0, 1)));
        assertFalse(pos.contains(new Position(2, 1)));
        assertFalse(pos.contains(new Position(5, 1)));
    }
    @Test
    void testEscapeFromCheck() {
        Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        Piece blackRook = pieceFactory.createPiece(ROOK, new Position(4, 1), BLACK);
        Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        List<Position> pos = control.removeMoveInCheck(chessboard, blackRook, blackRook.getAllPossiblePositions(chessboard));
        assertTrue(pos.contains(new Position(0, 1)));
        assertFalse(pos.contains(new Position(2, 1)));
        assertFalse(pos.contains(new Position(5, 1)));
    }
}
