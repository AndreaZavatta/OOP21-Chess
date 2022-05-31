package chessboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import model.promotion.Promotion;
import model.promotion.PromotionImpl;

class ChessboardTest {

    private final PieceFactory pieceFct = new PieceFactoryImpl();
    private final ChessboardFactory chessboardFct = new ChessboardFactoryImpl();
    private final Promotion promotionController = new PromotionImpl();

    @Test
    void creationChessboard() {
        final Chessboard board = chessboardFct.createNormalCB();
        assertEquals(32, board.getAllPieces().size());
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getSide().equals(Side.WHITE)).count());
        assertEquals(16, board.getAllPieces().stream().filter(x -> x.getSide().equals(Side.BLACK)).count());
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
                .getAllPossiblePositions(board).contains(Position.createNumericPosition(Numbers.FIVE, -1)));

        assertFalse(board.getPieceOnPosition(Position.createNewPosition("a8")).get()
                .getAllPossiblePositions(board).contains(Position.createNumericPosition(Numbers.ZERO, 8)));
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

    @Test
    void checkPromotion() {
        final Chessboard board = chessboardFct.createTestCB(
                            List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.WHITE),
                                  pieceFct.createPiece(Name.KING, Position.createNewPosition("h1"), Side.BLACK),
                                  pieceFct.createPiece(Name.PAWN, Position.createNewPosition("a7"), Side.WHITE)));

        board.move(Position.createNewPosition("a7"), Position.createNewPosition("a8"));
        assertTrue(promotionController.checkForPromotion(board.getAllPieces()).isPresent());
        assertEquals(pieceFct.createPiece(Name.PAWN, Position.createNewPosition("a8"), Side.WHITE),
                    promotionController.checkForPromotion(board.getAllPieces()).get());

        board.promotion(Name.QUEEN);
        assertEquals(pieceFct.createPiece(Name.QUEEN, Position.createNewPosition("a8"), Side.WHITE),
                board.getPieceOnPosition(Position.createNewPosition("a8")).get());
    }

    @Test
    void checkEquals1() {
        final Chessboard chess1 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK),
                               pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE)));
        final Chessboard chess2 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK),
                                pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE)));

        assertEquals(chess1, chess2);
    }

    @Test
    void checkEquals2() {
        final Chessboard chess1 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK),
                               pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE)));
        final Chessboard chess2 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK),
                                pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE),
                                pieceFct.createPiece(Name.BISHOP, Position.createNewPosition("h2"), Side.WHITE)));

        assertNotEquals(chess1, chess2);
    }

    @Test
    void checkEquals3() {
        final Chessboard chess1 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK),
                               pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE)));
        final Chessboard chess2 = chessboardFct.createTestCB(
                        List.of(pieceFct.createPiece(Name.KING, Position.createNewPosition("a2"), Side.WHITE),
                                pieceFct.createPiece(Name.KING, Position.createNewPosition("a1"), Side.BLACK)));

        assertEquals(chess1, chess2);
    }
}
