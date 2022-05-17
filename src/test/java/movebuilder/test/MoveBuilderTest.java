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
import static piece.utils.Name.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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
   private List<Piece> list;
   /**
 * @throws IllegalMoveException 
    * 
    */

   @BeforeAll
   void init() {
       list = new ArrayList<Piece>();
   }
   @Test
   void testBishopMove() throws IllegalMoveException {
       initBishopTest();
       moveBuilder.piece(pieceFact.createPiece(BISHOP, new Position(3, 4), WHITE))
       .destination(new Position(5, 6))
       .build(chessboard);
       assertEquals("Bf2", moveBuilder.toString());
   }
   @Test
   void testDisambiguousMove() throws IllegalMoveException {
       initDisambiguousMoveTest();
       moveBuilder.piece(pieceFact.createPiece(KNIGHT, new Position(3, 4), WHITE))
       .destination(new Position(4, 2))
       .build(chessboard);
       assertEquals("N3e6", moveBuilder.toString());
   }

   private void initDisambiguousMoveTest() {
       list.add(pieceFact.createPiece(KNIGHT, new Position(3, 4), WHITE));
       list.add(pieceFact.createPiece(KNIGHT, new Position(5, 4), WHITE));
       list.add(pieceFact.createPiece(Name.KING, new Position(1, 1), BLACK));
       list.add(pieceFact.createPiece(Name.KING, new Position(6, 6), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   private void initBishopTest() {
       list.add(pieceFact.createPiece(BISHOP, new Position(3, 4), WHITE));
       list.add(pieceFact.createPiece(BISHOP, new Position(4, 3), BLACK));
       list.add(pieceFact.createPiece(Name.KING, new Position(1, 1), BLACK));
       list.add(pieceFact.createPiece(Name.KING, new Position(6, 6), WHITE));
       chessboard = boardFactory.createTestCB(list);
}
}

