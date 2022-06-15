package castling;

import board.Castling;
import board.CastlingImpl;
import board.Chessboard;
import board.ChessboardFactoryImpl;
import board.ChessboardFactory;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 
 * This class tests some situations in which it is possible to castle.
 *
 */
public class CastlingTest {
    private final PieceFactory factory = new PieceFactoryImpl();
    private  final ChessboardFactory board = new ChessboardFactoryImpl();
    private final Castling castle = new CastlingImpl();
    @Test
    void basicCastlingTest() {
        final Chessboard board = this.board.createNormalCB();
        final Piece whiteKing = factory.createPiece(Name.KING, Position.createNewPosition("e1"), Side.WHITE);
        board.move(Position.createNewPosition("e2"), Position.createNewPosition("e3"));
        board.move(Position.createNewPosition("e7"), Position.createNewPosition("e6"));
        board.move(Position.createNewPosition("g1"), Position.createNewPosition("h3"));
        board.move(Position.createNewPosition("d7"), Position.createNewPosition("d6"));
        board.move(Position.createNewPosition("g2"), Position.createNewPosition("g4"));
        board.move(Position.createNewPosition("c7"), Position.createNewPosition("c6"));
        board.move(Position.createNewPosition("f1"), Position.createNewPosition("g2"));
        board.move(Position.createNewPosition("c8"), Position.createNewPosition("d7"));

        assertTrue(castle.canCastle(board, whiteKing, Numbers.SEVEN));
    }

     @Test
    void castleWithPiecesInTheWay() {
         final Chessboard board = this.board.createNormalCB();
         final Piece whiteKing = factory.createPiece(Name.KING, Position.createNewPosition("e1"), Side.WHITE);
         board.move(Position.createNewPosition("e2"), Position.createNewPosition("e3"));
         board.move(Position.createNewPosition("e7"), Position.createNewPosition("e6"));
         board.move(Position.createNewPosition("g1"), Position.createNewPosition("h3"));
         board.move(Position.createNewPosition("d7"), Position.createNewPosition("d6"));
         board.move(Position.createNewPosition("g2"), Position.createNewPosition("g4"));
         board.move(Position.createNewPosition("c7"), Position.createNewPosition("c6"));
         board.move(Position.createNewPosition("b1"), Position.createNewPosition("c3"));
         board.move(Position.createNewPosition("c8"), Position.createNewPosition("d7"));

         assertFalse(castle.canCastle(board, whiteKing, Numbers.SEVEN));
     }

     @Test
    void castleWhileAttacked() {
         final Chessboard board = this.board.createNormalCB();
         final Piece blackKing = factory.createPiece(Name.KING, Position.createNewPosition("e8"), Side.BLACK);
         board.move(Position.createNewPosition("e2"), Position.createNewPosition("e3"));
         board.move(Position.createNewPosition("e7"), Position.createNewPosition("e5"));
         board.move(Position.createNewPosition("g2"), Position.createNewPosition("g3"));
         board.move(Position.createNewPosition("b7"), Position.createNewPosition("b5"));
         board.move(Position.createNewPosition("f1"), Position.createNewPosition("h3"));
         board.move(Position.createNewPosition("d7"), Position.createNewPosition("d6"));
         board.move(Position.createNewPosition("b1"), Position.createNewPosition("c3"));
         board.move(Position.createNewPosition("c8"), Position.createNewPosition("a6"));
         board.move(Position.createNewPosition("b2"), Position.createNewPosition("b3"));
         board.move(Position.createNewPosition("b8"), Position.createNewPosition("c6"));
         board.move(Position.createNewPosition("c1"), Position.createNewPosition("b2"));
         board.move(Position.createNewPosition("d8"), Position.createNewPosition("e7"));
         board.move(Position.createNewPosition("d1"), Position.createNewPosition("e2"));

         assertFalse(castle.canCastle(board, blackKing, 0));
     }
}
