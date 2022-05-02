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
        final Piece bishop1 = factory.createPiece(Name.BISHOP, new Position(2, 2), Color.BLACK);
        list.add(bishop);
        list.add(bishop1);
        assertNotNull(bishop.getAllPossiblePositions(board.createTestCB(list)));
        bishop.getAllPossiblePositions(board.createTestCB(list)).forEach(x -> System.out.println(x));
        System.out.println(bishop);
    }

}
