package pieces.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class PawnTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testNormalMovementWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(0, 5), new Position(0, 4));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(0, 6), Color.WHITE);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testNormalMovementBlackPawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(0, 1), new Position(0, 2));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(0, 0), Color.BLACK);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

}
