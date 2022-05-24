package movebuilder.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static piece.utils.Position.createNewPosition;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import chess.parsers.Move;
import chess.parsers.MoveBuilder;
import exceptions.IllegalMoveException;
import piece.utils.Name;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;
import static piece.utils.Side.WHITE;
import static piece.utils.Side.BLACK;
import static piece.utils.Name.PAWN;
import static piece.utils.Name.QUEEN;
import static piece.utils.Name.BISHOP;
import static piece.utils.Name.KNIGHT;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 *
 */
class MoveBuilderTest {
   private final Move moveBuilder = new MoveBuilder();
   private final PieceFactory pieceFact = new PieceFactoryImpl();
   private final ChessboardFactory boardFactory = new ChessboardFactoryImpl();
   private Chessboard chessboard;
   private static List<Piece> list = new ArrayList<Piece>();
   /**
 * @throws IllegalMoveException 
    * 
    */

   @Test
   void testPawnAdvancementMove() {
       initPawnAdvancementMove();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("d4")));
       assertEquals("d4", moveBuilder.toString());
   }
   private void initPawnAdvancementMove() {
       list.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testPromotionMove() {
       initPromotionMove();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE))
                 .promotion(QUEEN)
                 .destination(createNewPosition("b8")));
       assertEquals("b8=Q", moveBuilder.toString());
   }
   private void initPromotionMove() {
       list.add(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("e3"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testDrawOffer() {
       wrapBuild(moveBuilder.drawOffer());
       assertEquals("(=)", moveBuilder.toString());
   }
   @Test
   void testCheckMove() {
       initTestCheckMove();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE))
                 .check()
                 .destination(createNewPosition("b3")));
       assertEquals("Qb3+", moveBuilder.toString());
   }
   private void initTestCheckMove() {
       list.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testCheckmate() {
       initTestCheckmate();
           wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE))
                    .checkmate()
                    .destination(createNewPosition("b3")));
       assertEquals("Qcb3#", moveBuilder.toString());
   }
   private void initTestCheckmate() {
       list.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE));
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("a4"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("a8"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testPawnCaptureMove() {
       initPawnCaptureMove();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK))
                 .capture()
                 .destination(createNewPosition("e3")));
       assertEquals("dxe3", moveBuilder.toString());
   }
   private void initPawnCaptureMove() {
       list.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       list.add(pieceFact.createPiece(PAWN, createNewPosition("e3"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testKingsideCastling() {
       wrapBuild(moveBuilder.kingSideCastling());
       assertEquals("0-0", moveBuilder.toString());
   }
   @Test
   void testQueensideCastling() {
       wrapBuild(moveBuilder.queenSideCastling());
       assertEquals("0-0-0", moveBuilder.toString());
   }
   @Test
   void testBishopMove() {
       initBishopTest();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("f2")));
       assertEquals("Bf2", moveBuilder.toString());
   }
   private void initBishopTest() {
       list.add(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE));
       list.add(pieceFact.createPiece(BISHOP, createNewPosition("e5"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
}
   @Test
   void testDisambiguousMoveSameRow() {
       initTestDisambiguousMoveSameRow();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("e6")));
       assertEquals("Nde6", moveBuilder.toString());
   }

   private void initTestDisambiguousMoveSameRow() {
       list.add(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE));
       list.add(pieceFact.createPiece(KNIGHT, createNewPosition("f4"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testDisambiguousMoveSameRowAndCol() {
       initTestDisambiguousMoveSameRowAndCol();
       wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("e4")));
       assertEquals("Qd4e4", moveBuilder.toString());
   }
   private void initTestDisambiguousMoveSameRowAndCol() {
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE));
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("f4"), WHITE));
       list.add(pieceFact.createPiece(QUEEN, createNewPosition("d5"), WHITE));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       list.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(list);
   }
   @Test
   void testIllegalMoveExceptionDestinationNotFound() {
       assertThrows(
               IllegalMoveException.class,
               () -> moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE)).build(chessboard));
   }
   @Test
   void testIllegalMoveExceptionPieceNotFound() {
       assertThrows(
               IllegalMoveException.class,
               () -> moveBuilder.destination(createNewPosition("d4")).build(chessboard));
   }

   private void wrapBuild(final Move move) {
       try {
           move.build(chessboard);
       } catch (IllegalMoveException e) {
           fail();
       }
   }


}

