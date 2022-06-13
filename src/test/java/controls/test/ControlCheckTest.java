package controls.test;
import static model.piece.utils.Name.KING;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Name.ROOK;
import static model.piece.utils.Name.KNIGHT;
import static model.piece.utils.Name.QUEEN;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
import static model.piece.utils.Position.createNewPosition;
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
import model.piece.utils.Position;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

class ControlCheckTest {
    private Chessboard chessboard;
    private static final ChessboardFactory CHESSBOARD_FACTORY = new ChessboardFactoryImpl();
    private static final PieceFactory PIECE_FACTORY = new PieceFactoryImpl();
    private static final ControlCheck CONTROL = new ControlCheckImpl();

    
    @Test
    void testKingNotFoundException() {
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e8"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(whiteRook));
        assertThrows(
                KingNotFoundException.class,
                () -> CONTROL.isInCheck(chessboard, BLACK)
         );

    }
    @Test
    void isInCheckTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e8"), WHITE);
        final Piece whiteKing = PIECE_FACTORY.createPiece(KING, createNewPosition("f4"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(CONTROL.isInCheck(chessboard, BLACK));
    }
    @Test
    void beInCheckEnemyAttackKingTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e8"), WHITE);
        final Piece whiteKing = PIECE_FACTORY.createPiece(KING, createNewPosition("f4"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, whiteRook, whiteKing));
        assertTrue(CONTROL.isInCheck(chessboard, BLACK));
        assertFalse(CONTROL.isInCheck(chessboard, WHITE));
    }
    @Test
    void ableToMovePinOnKingFalse() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackBishop = PIECE_FACTORY.createPiece(BISHOP, createNewPosition("d8"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e8"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackBishop);
        assertEquals(pos, List.of());
    }
    @Test
    void ableToMoveNoPinOnKingTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackBishop = PIECE_FACTORY.createPiece(BISHOP, createNewPosition("d8"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e6"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackBishop, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackBishop);
        assertTrue(pos.containsAll(List.of(createNewPosition("f6"), createNewPosition("e7"), createNewPosition("g5"), createNewPosition("h4"), createNewPosition("c7"), createNewPosition("b6"), createNewPosition("a5"))));
    }
    @Test
    void ableToMoveOnlyToCoverCheckTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece blackRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("e7"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("a6"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackRook, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackRook);
        assertEquals(pos, List.of(createNewPosition("a7")));
    }
    @Test
    void ableToMoveOnlyToEscapeFromCheckTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("a8"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("a6"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackKing);
        assertTrue(pos.containsAll(List.of(createNewPosition("b7"), createNewPosition("b8"))));
    }
    @Test
    void ableToMoveToCoverCheckTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("c6"), BLACK);
        final Piece blackKnight = PIECE_FACTORY.createPiece(KNIGHT, createNewPosition("e3"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackKnight, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackKnight);
        assertEquals(pos, List.of(createNewPosition("c4")));
    }
    @Test
    void ableToMoveToCoverCheckOrToEatTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("c6"), BLACK);
        final Piece blackKnight = PIECE_FACTORY.createPiece(KNIGHT, createNewPosition("e4"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackKnight, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackKnight);
        assertTrue(pos.containsAll(List.of(createNewPosition("c5"), createNewPosition("c3"))));
    }
    @Test
    void ableToMoveOnlyVerticallyTrue() {
        final Piece blackKing = PIECE_FACTORY.createPiece(KING, createNewPosition("c7"), BLACK);
        final Piece blackQueen = PIECE_FACTORY.createPiece(QUEEN, createNewPosition("c5"), BLACK);
        final Piece whiteRook = PIECE_FACTORY.createPiece(ROOK, createNewPosition("c3"), WHITE);
        chessboard = CHESSBOARD_FACTORY.createTestCB(List.of(blackKing, blackQueen, whiteRook));
        final List<Position> pos = CONTROL.controlledMoves(chessboard, blackQueen);
        assertTrue(pos.containsAll(List.of(createNewPosition("c6"), createNewPosition("c3"), createNewPosition("c4"))));
    }

}
