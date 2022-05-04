package pieces.test;


import static org.junit.jupiter.api.Assertions.assertNotNull;

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

class BishopTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();
    private final List<Piece> list = new ArrayList<>();

    @Test
    void test() {
        final Piece bishop = factory.createPiece(Name.BISHOP, new Position(0, 0), Color.WHITE);
        final Piece king = factory.createPiece(Name.KING, new Position(2, 2), Color.WHITE);
        final Piece knight = factory.createPiece(Name.KNIGHT, new Position(2, 0), Color.WHITE);
        //final Piece queen = factory.createPiece(Name.QUEEN, new Position(4, 4), Color.BLACK);
        //final Piece rook = factory.createPiece(Name.ROOK, new Position(7, 7), Color.BLACK);
        final Piece pawn = factory.createPiece(Name.PAWN, new Position(1, 1), Color.BLACK);
        //list.add(bishop);
        list.add(king);
        //list.add(queen);
        list.add(pawn);
        list.add(knight);
        assertNotNull(bishop.getAllPossiblePositions(board.createTestCB(list)));
        pawn.getAllPossiblePositions(board.createTestCB(list)).forEach(x -> System.out.println(x));
        //System.out.println(bishop);
    }

}
