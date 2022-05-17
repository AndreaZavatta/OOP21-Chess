package movebuilder.test;

import static org.junit.jupiter.api.Assertions.*;


import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import exceptions.IllegalMoveException;
import piece.utils.Move;
import piece.utils.MoveBuilder;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

import static piece.utils.Side.WHITE;
import static piece.utils.Side.BLACK;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 *
 */
public class MoveBuilderTest {
   private final Move moveBuilder = new MoveBuilder();
   private final PieceFactory pieceFact = new PieceFactoryImpl();
   private final ChessboardFactory boardFactory = new ChessboardFactoryImpl();
   private Chessboard chessboard;
   /**
    * 
    */

   @Test
   void testBishopMove() {
       initBishopTest();
       try {
           moveBuilder.piece(pieceFact.createPiece(Name.BISHOP, new Position(3, 4), WHITE))
           .destination(new Position(5, 6))
           .build(chessboard);
       } catch (IllegalMoveException e) {
           System.out.println(e.getMessage());
       }
       System.out.println(moveBuilder);
   }

private void initBishopTest() {
    List<Piece> list = new ArrayList();
       list.add(pieceFact.createPiece(Name.BISHOP, new Position(3, 4), WHITE));
       list.add(pieceFact.createPiece(Name.BISHOP, new Position(4, 3), BLACK));
       list.add(pieceFact.createPiece(Name.KING, new Position(1, 1), BLACK));
       list.add(pieceFact.createPiece(Name.KING, new Position(6, 6), WHITE));
       chessboard = boardFactory.createTestCB(list);
}
}

