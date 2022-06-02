package endgame;

import board.*;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndGameTest {

    private final ChessboardFactory board = new ChessboardFactoryImpl();
    private final EndGame endGame = new EndGameImpl();

    @Test
    void checkmateTest() {
        final Chessboard board = this.board.createNormalCB();
        board.move(Position.createNewPosition("f2"), Position.createNewPosition("f3"));
        board.move(Position.createNewPosition("e7"), Position.createNewPosition("e5"));
        board.move(Position.createNewPosition("g2"), Position.createNewPosition("g4"));
        board.move(Position.createNewPosition("d8"), Position.createNewPosition("h4"));

        assertTrue(endGame.isCheckmate(Side.WHITE, board));
    }

    @Test
    void insufficientMaterialDrawTest(){
        final List<Piece> list =  new ArrayList<>();

        final PieceFactory pieces = new PieceFactoryImpl();
        var whiteKing = pieces.createPiece(Name.KING, Position.createNewPosition("c2"), Side.WHITE);
        list.add(whiteKing);
        var whiteBishop = pieces.createPiece(Name.BISHOP, Position.createNewPosition("d3"), Side.WHITE);
        list.add(whiteBishop);
        var whitePawn = pieces.createPiece(Name.PAWN, Position.createNewPosition("f2"), Side.WHITE);
        list.add(whitePawn);
        var blackKing = pieces.createPiece(Name.KING, Position.createNewPosition("g5"), Side.BLACK);
        list.add(blackKing);
        var blackKnight = pieces.createPiece(Name.KNIGHT, Position.createNewPosition("g4"), Side.BLACK);
        list.add(blackKnight);

        final Chessboard board = this.board.createTestCB(list);
        board.move(Position.createNewPosition("g4"), Position.createNewPosition("f2"));

        assertTrue(endGame.isDrawByInsufficientMaterial(board));
    }

    @Test
    void stalemateTest() {
        final List<Piece> list =  new ArrayList<>();

        final PieceFactory pieces = new PieceFactoryImpl();
        var whiteKing = pieces.createPiece(Name.KING, Position.createNewPosition("b6"), Side.WHITE);
        list.add(whiteKing);
        var whitePawn = pieces.createPiece(Name.PAWN, Position.createNewPosition("a7"), Side.WHITE);
        list.add(whitePawn);
        var whiteBishop = pieces.createPiece(Name.BISHOP, Position.createNewPosition("c5"), Side.WHITE);
        list.add(whiteBishop);
        var blackKing = pieces.createPiece(Name.KING, Position.createNewPosition("a8"), Side.BLACK);
        list.add(blackKing);

        final Chessboard board = this.board.createTestCB(list);
        board.move(Position.createNewPosition("c5"), Position.createNewPosition("d6"));

        assertTrue(endGame.isStalemate(Side.BLACK, board));
    }

}
