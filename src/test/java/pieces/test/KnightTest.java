package pieces.test;

import model.board.ChessboardFactory;
import model.board.ChessboardFactoryImpl;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


class KnightTest {
    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testKnightFullMove() {
        final List<Piece> list = new ArrayList<>();
        final List<Position> l = List.of(Position.createNewPosition("b6"),
                                         Position.createNewPosition("d6"),
                                         Position.createNewPosition("e5"),
                                         Position.createNewPosition("e3"),
                                         Position.createNewPosition("b2"),
                                         Position.createNewPosition("d2"),
                                         Position.createNewPosition("a5"),
                                         Position.createNewPosition("a3"));
        final Piece knight = factory.createPiece(Name.KNIGHT, Position.createNewPosition("c4"), Side.WHITE);
        list.add(knight);
        assertEquals(l, knight.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testOnNormalBoard() {
        final Chessboard chessboard = board.createNormalCB();
        final List<Piece> list = chessboard.getAllPieces();
        final Piece knight = list.stream().filter(x -> x.getPosition().equals(Position.createNewPosition("b1")) 
                && x.getName().equals(Name.KNIGHT)).findFirst().get();
        final List<Position> l = List.of(Position.createNewPosition("a3"),
                                         Position.createNewPosition("c3"));
        assertEquals(l, knight.getAllPossiblePositions(chessboard));
    }
}
