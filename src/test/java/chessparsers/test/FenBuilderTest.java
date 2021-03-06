package chessparsers.test;

import static model.piece.utils.Name.ROOK;
import static model.piece.utils.Name.KING;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Name.PAWN;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static model.piece.utils.Position.createNewPosition;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.board.Chessboard;
import model.board.ChessboardFactory;
import model.board.ChessboardFactoryImpl;
import model.parsers.Fen;
import model.parsers.FenBuilder;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

class FenBuilderTest {
    private final Fen fenBuilder = new FenBuilder();
    private final PieceFactory pieceFact = new PieceFactoryImpl();
    private final ChessboardFactory boardFactory = new ChessboardFactoryImpl();

    @Test
    void testInitialPosition() {
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 
                fenBuilder
                .activeColor(WHITE)
                .build(boardFactory.createNormalCB()));
    }
    @Test
    void testFirstPosition() {
        final List<Piece> list =
                List.of(pieceFact.createPiece(ROOK, createNewPosition("a8"), BLACK),
                pieceFact.createPiece(KING, createNewPosition("e8"), BLACK),
                pieceFact.createPiece(BISHOP, createNewPosition("h8"), BLACK),
                pieceFact.createPiece(ROOK, createNewPosition("a1"), WHITE),
                pieceFact.createPiece(KING, createNewPosition("d2"), WHITE));
        final Chessboard board = boardFactory.createTestCB(list);
        assertEquals("r3k2b/8/8/8/8/8/3K4/R7 w q - 0 1", 
                fenBuilder.activeColor(WHITE)
                .blackCastlingKingSide()
                .whiteCastlingKingSide()
                .whiteCastlingQueenSide()
                .build(board));
    }
    @Test
    void testSecondPosition() {
        final List<Piece> list =
                List.of(pieceFact.createPiece(KING, createNewPosition("b5"), BLACK),
                pieceFact.createPiece(PAWN, createNewPosition("a2"), BLACK),
                pieceFact.createPiece(KING, createNewPosition("f2"), WHITE),
                pieceFact.createPiece(PAWN, createNewPosition("h4"), WHITE));
        final Chessboard board = boardFactory.createTestCB(list);
        assertEquals("8/8/8/1k6/7P/8/p4K2/8 b - - 0 1", 
                fenBuilder.activeColor(BLACK)
                .blackCastlingKingSide()
                .blackCastlingQueenSide()
                .whiteCastlingKingSide()
                .whiteCastlingQueenSide()
                .build(board));

    }
    @Test
    void testFoolMate() {
        final Chessboard board = boardFactory.createNormalCB();
        board.move(createNewPosition("f2"), createNewPosition("f3"));
        board.move(createNewPosition("e7"), createNewPosition("e5"));
        board.move(createNewPosition("g2"), createNewPosition("g4"));
        board.move(createNewPosition("d8"), createNewPosition("h4"));
        assertEquals("rnb1kbnr/pppp1ppp/8/4p3/6Pq/5P2/PPPPP2P/RNBQKBNR w KQkq - 0 1", 
                fenBuilder.activeColor(WHITE).build(board));
    }
    @Test
    void testPhilidorPosition() {
        final List<Piece> list =
                List.of(pieceFact.createPiece(KING, createNewPosition("f4"), BLACK),
                pieceFact.createPiece(PAWN, createNewPosition("e4"), BLACK),
                pieceFact.createPiece(ROOK, createNewPosition("h2"), BLACK),
                pieceFact.createPiece(ROOK, createNewPosition("a3"), WHITE),
                pieceFact.createPiece(KING, createNewPosition("e1"), WHITE));
        final Chessboard board = boardFactory.createTestCB(list);
        assertEquals("8/8/8/8/4pk2/R7/7r/4K3 b - - 0 1", 
                fenBuilder.activeColor(BLACK)
                .blackCastlingKingSide()
                .blackCastlingQueenSide()
                .whiteCastlingKingSide()
                .whiteCastlingQueenSide()
                .build(board));
    }
}
