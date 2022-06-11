package pieces.test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testKnightFullMove() {
        final List<Piece> list = new ArrayList<>();
        final Piece knight = factory.createPiece(Name.KNIGHT, Position.createNewPosition("c4"), Side.WHITE);
        list.add(knight);


    }
}