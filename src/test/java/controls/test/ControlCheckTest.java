package controls.test;
import static piece.utils.Name.KING;
import static piece.utils.Name.BISHOP;
import static piece.utils.Name.ROOK;
import static piece.utils.Name.KNIGHT;
import static piece.utils.Name.QUEEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
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
    void testKingNotFoundException() {
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 0), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(whiteRook));
        assertThrows(
                KingNotFoundException.class,
                () -> control.isInCheck(chessboard, BLACK)
         );

    }

    @Test
    void beInCheckEnemyAttackKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 0), WHITE);
        final Piece whiteKing = pieceFactory.createPiece(KING, new Position(5, 4), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(control.isInCheck(chessboard, BLACK));
        assertFalse(control.isInCheck(chessboard, WHITE));
    }
    @Test
    void ableToMovePinOnKingFalse() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(3, 0), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 0), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackBishop);
        assertEquals(pos, List.of());
    }
    @Test
    void ableToMoveNoPinOnKingTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        final Piece blackBishop = pieceFactory.createPiece(BISHOP, new Position(3, 0), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(4, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackBishop);
        assertTrue(pos.containsAll(List.of(new Position(5, 2), new Position(4, 1), new Position(6, 3), new Position(7, 4), new Position(2, 1), new Position(1, 2), new Position(0,3))));
    }
    @Test
    void ableToMoveOnlyToCoverCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        final Piece blackRook = pieceFactory.createPiece(ROOK, new Position(4, 1), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackRook, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackRook);
        assertEquals(pos, List.of(new Position(0, 1)));
    }
    @Test
    void ableToMoveOnlyToEscapeFromCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(0, 0), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(0, 2), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackKing);
        assertTrue(pos.containsAll(List.of(new Position(1, 1), new Position(1, 0))));
    }
    @Test
    void ableToMoveToCoverCheckTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(2, 2), BLACK);
        final Piece blackKinght = pieceFactory.createPiece(KNIGHT, new Position(4, 5), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(2, 5), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackKinght, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackKinght);
        assertEquals(pos, List.of(new Position(2, 4)));
    }
    @Test
    void ableToMoveToCoverCheckOrToEatTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(2, 2), BLACK);
        final Piece blackKinght = pieceFactory.createPiece(KNIGHT, new Position(4, 4), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(2, 5), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackKinght, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackKinght);
        assertTrue(pos.containsAll(List.of(new Position(2, 3), new Position(2, 5))));
    }
    @Test
    void ableToMoveOnlyVerticallyTrue() {
        final Piece blackKing = pieceFactory.createPiece(KING, new Position(2, 1), BLACK);
        final Piece blackQueen = pieceFactory.createPiece(QUEEN, new Position(2, 3), BLACK);
        final Piece whiteRook = pieceFactory.createPiece(ROOK, new Position(2, 5), WHITE);
        chessboard = chessboardFactory.createTestCB(List.of(blackKing, blackQueen, whiteRook));
        final List<Position> pos = control.removeMovesInCheck(chessboard, blackQueen);
        assertTrue(pos.containsAll(List.of(new Position(2, 2), new Position(2, 5), new Position(2, 4))));
    }

}
