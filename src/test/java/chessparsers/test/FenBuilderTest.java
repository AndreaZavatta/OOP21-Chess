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
                .activeColor(WHITE)
                .build(boardFactory.createNormalCB()));
    }
    @Test
    void testFirstPosition() {
        List<Piece> list = 
                List.of(pieceFact.createPiece(ROOK, createNewPosition("a8"), BLACK),
                pieceFact.createPiece(KING, createNewPosition("e8"), BLACK),
                pieceFact.createPiece(BISHOP, createNewPosition("h8"), BLACK),
                pieceFact.createPiece(ROOK, createNewPosition("a1"), WHITE),
                pieceFact.createPiece(KING, createNewPosition("d2"), WHITE));
        var board = boardFactory.createTestCB(list);
        assertEquals("r3k2b/8/8/8/8/8/3K4/R7 w q - 0 1", 
                fenBuilder.activeColor(WHITE)
                .blackCastledKingside()
                .whiteCastledKingside()
                .whiteCastledQueenside()
                .build(board));
    }
    @Test
    void testSecondPosition() {
        List<Piece> list = 
                List.of(pieceFact.createPiece(KING, createNewPosition("b5"), BLACK),
                pieceFact.createPiece(PAWN, createNewPosition("a2"), BLACK),
                pieceFact.createPiece(KING, createNewPosition("f2"), WHITE),
                pieceFact.createPiece(PAWN, createNewPosition("h4"), WHITE));
        var board = boardFactory.createTestCB(list);
        assertEquals("8/8/8/1k6/7P/8/p4K2/8 b - - 0 1", 
                fenBuilder.activeColor(BLACK)
                .blackCastledKingside()
                .blackCastledQueenside()
                .whiteCastledKingside()
                .whiteCastledQueenside()
                .build(board));

    }
}
