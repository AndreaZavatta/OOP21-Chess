package chessparsers.test;

import static org.junit.jupiter.api.Assertions.*;
import static piece.utils.Name.*;
import static piece.utils.Side.*;
import static piece.utils.Position.createNewPosition;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import chess.parsers.Fen;
import chess.parsers.FenBuilder;
import piece.utils.Position;
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
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 
                fenBuilder.halfMoveClock(0)
                .fullMoveNumber(1)
                .activeColor(WHITE)
                .build(boardFactory.createNormalCB()));
    }
    @Test
    void testFirstPosition() {
        List<Piece> list = List.of(pieceFact.createPiece(ROOK, createNewPosition("a8"), BLACK),
                                   pieceFact.createPiece(KING, createNewPosition("e8"), BLACK),
                                   pieceFact.createPiece(BISHOP, createNewPosition("h8"), BLACK),
                                   pieceFact.createPiece(ROOK, createNewPosition("a1"), WHITE),
                                   pieceFact.createPiece(KING, createNewPosition("d2"), WHITE));
        var board = boardFactory.createTestCB(list);
        System.out.println(fenBuilder.fullMoveNumber(1)
                .activeColor(WHITE)
                .blackCastledKingside()
                .whiteCastledKingside()
                .whiteCastledQueenside()
                .build(board));
        assertEquals("r3k2b/8/8/8/8/8/3K4/R7 w q - 0 1", 
                fenBuilder.fullMoveNumber(1)
                .activeColor(WHITE)
                .blackCastledKingside()
                .whiteCastledKingside()
                .whiteCastledQueenside()
                .build(board));
    }
}
