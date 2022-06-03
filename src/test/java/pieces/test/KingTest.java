package pieces.test;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import game.Game;
import game.GameImpl;
import org.junit.jupiter.api.Test;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import pair.Pair;
import user.User;
import user.UserImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private final PieceFactory factory = new PieceFactoryImpl();
    private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void testCastlingPositionWhiteSideLeft() {
        final List<Piece> list = new ArrayList<>();
        final Piece king = factory.createPiece(Name.KING, Position.createNewPosition("e1"), Side.WHITE);
        final Piece rook = factory.createPiece(Name.ROOK, Position.createNewPosition("h1"), Side.WHITE);
        list.add(king);
        list.add(rook);
        assertTrue(king.getAllPossiblePositions(board.createTestCB(list)).contains(Position.createNewPosition("g1")));
        king.setPosition(king.getPosition());
        assertFalse(king.getAllPossiblePositions(board.createTestCB(list)).contains(Position.createNewPosition("g1")));
    }

    @Test
    void testNormalMoves() {
        final List<Piece> list = new ArrayList<>();
        final Piece king = factory.createPiece(Name.KING, Position.createNewPosition("e4"), Side.WHITE);
        final List<Position> positionsCanGo = List.of(Position.createNewPosition("f4"),
                Position.createNewPosition("d4"),
                Position.createNewPosition("e3"),
                Position.createNewPosition("e5"),
                Position.createNewPosition("f3"),
                Position.createNewPosition("d5"),
                Position.createNewPosition("f5"),
                Position.createNewPosition("d3"));
        list.add(king);
        assertEquals(positionsCanGo, king.getAllPossiblePositions(board.createTestCB(list)));
    }

    @Test
    void testMove(){
        Pair<User, Side> player = new Pair<>(new UserImpl("a"), Side.WHITE);
        Pair<User, Side> player2 = new Pair<>(new UserImpl("a"), Side.BLACK);
        Game game = new GameImpl(player,player2);
        //Chessboard chessboard = fenToBoard.getBoard("rnbqkb1r/pppp1ppp/5n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 1");
        //chessboard.move(Position.createNewPosition("f8"), Position.createNewPosition("c5"));
        //System.out.println(chessboard);
        game.nextMove(Position.createNewPosition("g2"), Position.createNewPosition("g4"));
        game.nextMove(Position.createNewPosition("g7"), Position.createNewPosition("g5"));
        game.nextMove(Position.createNewPosition("f2"), Position.createNewPosition("f4"));
        game.nextMove(Position.createNewPosition("f7"), Position.createNewPosition("f5"));

        game.nextMove(Position.createNewPosition("g1"), Position.createNewPosition("h3"));
        game.nextMove(Position.createNewPosition("g8"), Position.createNewPosition("h6"));
        game.nextMove(Position.createNewPosition("f1"), Position.createNewPosition("g2"));
        game.nextMove(Position.createNewPosition("f8"), Position.createNewPosition("g7"));
    }
}
