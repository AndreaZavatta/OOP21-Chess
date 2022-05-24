package chessparsers.test;

import static org.junit.jupiter.api.Assertions.*;
import static piece.utils.Side.WHITE;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import chess.parsers.Fen;
import chess.parsers.FenBuilder;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class FenBuilderTest {

    private final Fen fenBuilder = new FenBuilder();
    private final PieceFactory pieceFact = new PieceFactoryImpl();
    private final ChessboardFactory boardFactory = new ChessboardFactoryImpl();
    private static List<Piece> list = new ArrayList<Piece>();
    @Test
    void testInitialPosition() {
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", fenBuilder.halfMove("0 1").activeColor(WHITE).build(boardFactory.createNormalCB()));
    }
}
