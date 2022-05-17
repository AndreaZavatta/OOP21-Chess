package pieces.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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

class PawnTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testNormalMovementWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(0, 5), new Position(0, 4));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(0, 6), Side.WHITE);
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
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(0, 0), Side.BLACK);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testOnePositionMovementWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(2, 1));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(2, 2), Side.WHITE);
        list.add(pawn);
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testOnePositionMovementBlackPawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(2, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(2, 2), Side.BLACK);
        list.add(pawn);
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testGoFowardTwoTilesAndCanEatLeftAndRightBlackPawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(3, 3), new Position(1, 3), new Position(2, 3), new Position(2, 4));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(2, 2), Side.BLACK);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(3, 3), Side.WHITE);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(1, 3), Side.WHITE);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testGoFowardTwoTilesAndCanEatLeftAndRightWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(4, 4), new Position(6, 4), new Position(5, 4), new Position(5, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(5, 5), Side.WHITE);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 4), Side.BLACK);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(6, 4), Side.BLACK);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testGoFowardOneTileAndCanEatLeftAndRightWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(4, 4), new Position(6, 4), new Position(5, 4));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(5, 5), Side.WHITE);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 4), Side.BLACK);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(6, 4), Side.BLACK);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testGoFowardOneTileAndCanEatLeftAndRightBlackPawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(3, 3), new Position(1, 3), new Position(2, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(2, 2), Side.BLACK);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(3, 3), Side.WHITE);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(1, 3), Side.WHITE);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testGoFowardTwoTilesAndCantEatBlackPawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(2, 3), new Position(2, 4));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(2, 2), Side.BLACK);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(3, 3), Side.BLACK);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(1, 3), Side.BLACK);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testGoFowardTwoTilesAndCantEatWhitePawn() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(5, 4), new Position(5, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(5, 5), Side.WHITE);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(4, 4), Side.WHITE);
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(6, 4), Side.WHITE);
        list.add(pawn);
        list.add(bishop1);
        list.add(bishop);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnOnEdgeNoPositionPossible() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(7, 7), Side.BLACK);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testWhitePawnOnEdgeNoPositionPossible() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(0, 0), Side.WHITE);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnSurrounded() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(4, 4), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, new Position(4, 3), Side.WHITE);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(3, 3), Side.WHITE);
        final Piece queen = factory.createPiece(Name.QUEEN, new Position(5, 3), Side.WHITE);
        final Piece knight = factory.createPiece(Name.KNIGHT, new Position(4, 5), Side.WHITE);
        final Piece pawn2 = factory.createPiece(Name.PAWN, new Position(4, 2), Side.WHITE);
        list.add(queen);
        list.add(knight);
        list.add(bishop);
        list.add(rook);
        list.add(pawn2);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnBlocked() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of();
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(4, 4), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, new Position(4, 3), Side.WHITE);
        list.add(rook);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnCantGoTwoTiles() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(4, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(4, 4), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, new Position(4, 2), Side.WHITE);
        list.add(rook);
        list.add(pawn);
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnSurroundedCanEatBothSide() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(new Position(3, 3), new Position(5, 3));
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(4, 4), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, new Position(4, 3), Side.WHITE);
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(3, 3), Side.BLACK);
        final Piece queen = factory.createPiece(Name.QUEEN, new Position(5, 3), Side.BLACK);
        final Piece knight = factory.createPiece(Name.KNIGHT, new Position(4, 5), Side.WHITE);
        final Piece pawn2 = factory.createPiece(Name.PAWN, new Position(4, 2), Side.WHITE);
        list.add(queen);
        list.add(knight);
        list.add(bishop);
        list.add(rook);
        list.add(pawn2);
        list.add(pawn);
        assertEquals(l, pawn.getAllPossiblePositions(board.createTestCB(list)));
        assertFalse(pawn.isMoved());
        pawn.setIsMoved();
        assertTrue(pawn.isMoved());
    }

    @Test
    void testBlackPawnOnNormalBoard() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece pawn = list.stream().filter(x -> x.getPosition().equals(new Position(1, 1)) 
                && x.getName().equals(Name.PAWN)).findFirst().get();
        final List<Position> l = List.of(new Position(1, 2), new Position(1, 3));
        assertEquals(l, pawn.getAllPossiblePositions(chessboard));
    }
}
