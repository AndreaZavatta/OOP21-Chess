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
import model.board.Chessboard;
import model.board.ChessboardFactory;
import model.board.ChessboardFactoryImpl;
import model.board.ControlCheck;
import model.board.ControlCheckImpl;
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
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k3R3/8/8/8/5K2/8/8/8 w - - 0 1");
        assertTrue(CONTROL.isInCheck(chessboard, BLACK));
    }
    @Test
    void beInCheckEnemyAttackKingTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k3R3/8/8/8/5K2/8/8/8 w - - 0 1");
        assertTrue(CONTROL.isInCheck(chessboard, BLACK));
        assertFalse(CONTROL.isInCheck(chessboard, WHITE));
    }
    @Test
    void ableToMovePinOnKingFalse() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k2bR3/8/8/8/5K2/8/8/8 b - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("d8")).get());
        assertEquals(pos, List.of());
    }
    @Test
    void ableToMoveNoPinOnKingTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k2b4/8/4R3/8/5K2/8/8/8 b - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("d8")).get());
        System.out.println(pos);
        assertTrue(pos.containsAll(List.of(createNewPosition("f6"), createNewPosition("e7"), createNewPosition("g5"), createNewPosition("h4"), createNewPosition("c7"), createNewPosition("b6"), createNewPosition("a5"))));
    }
    @Test
    void ableToMoveOnlyToCoverCheckTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k7/4r3/R7/8/5K2/8/8/8 w - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("e7")).get());
        assertEquals(pos, List.of(createNewPosition("a7")));
    }
    @Test
    void ableToMoveOnlyToEscapeFromCheckTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("k7/8/R7/8/5K2/8/8/8 w - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("a8")).get());
        assertTrue(pos.containsAll(List.of(createNewPosition("b7"), createNewPosition("b8"))));
    }
    @Test
    void ableToMoveToCoverCheckTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("8/8/2k5/6K1/8/2R1n3/8/8 b - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("e3")).get());
        assertEquals(pos, List.of(createNewPosition("c4")));
    }
    @Test
    void ableToMoveToCoverCheckOrToEatTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("8/8/2k5/6K1/4n3/2R5/8/8 w - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("e4")).get());
        assertTrue(pos.containsAll(List.of(createNewPosition("c5"), createNewPosition("c3"))));
    }
    @Test
    void ableToMoveOnlyVerticallyTrue() {
        chessboard = CHESSBOARD_FACTORY.createCBToFen("8/2k5/8/2q3K1/8/2R5/8/8 w - - 0 1");
        final List<Position> pos = CONTROL.controlledMoves(chessboard, chessboard.getPieceOnPosition(createNewPosition("c5")).get());
        assertTrue(pos.containsAll(List.of(createNewPosition("c6"), createNewPosition("c3"), createNewPosition("c4"))));
    }

}
