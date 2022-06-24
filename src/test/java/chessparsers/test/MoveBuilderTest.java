package chessparsers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static model.piece.utils.Position.createNewPosition;

import model.board.Chessboard;
import model.board.ChessboardFactory;
import model.board.ChessboardFactoryImpl;
import model.parsers.Move;
import model.parsers.MoveBuilder;
import model.exceptions.IllegalMoveException;
import model.piece.utils.Name;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import static model.piece.utils.Side.WHITE;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Name.PAWN;
import static model.piece.utils.Name.QUEEN;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Name.KNIGHT;
import java.util.ArrayList;
import java.util.List;
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
   private static final List<Piece> LIST = new ArrayList<>();

   /**
    * 
    */

   @Test
   void testPawnAdvancementMove() {
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("d4")));
       assertEquals("d4", moveBuilder.toString());
   }

   @Test
   void testPromotionMove() {
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("e3"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE))
                 .promotion(QUEEN)
                 .destination(createNewPosition("b8")));
       assertEquals("b8=Q", moveBuilder.toString());
   }

   @Test
   void testDrawOffer() {
       wrapException(moveBuilder.drawOffer());
       assertEquals("(=)", moveBuilder.toString());
   }
   @Test
   void testCheckMove() {
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE))
                 .check()
                 .destination(createNewPosition("b3")));
       assertEquals("Qb3+", moveBuilder.toString());
   }

   @Test
   void testCheckmate() {
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE));
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("a4"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("a8"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

           wrapException(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE))
                    .checkmate()
                    .destination(createNewPosition("b3")));
       assertEquals("Qcb3#", moveBuilder.toString());
   }

   @Test
   void testPawnCaptureMove() {
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       LIST.add(pieceFact.createPiece(PAWN, createNewPosition("e3"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK))
                 .capture()
                 .destination(createNewPosition("e3")));
       assertEquals("dxe3", moveBuilder.toString());
   }
   @Test
   void testKingSideCastling() {
       wrapException(moveBuilder.kingSideCastling());
       assertEquals("0-0", moveBuilder.toString());
   }
   @Test
   void testQueenSideCastling() {
       wrapException(moveBuilder.queenSideCastling());
       assertEquals("0-0-0", moveBuilder.toString());
   }
   @Test
   void testBishopMove() {
       LIST.add(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE));
       LIST.add(pieceFact.createPiece(BISHOP, createNewPosition("e5"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("f2")));
       assertEquals("Bf2", moveBuilder.toString());
   }

   @Test
   void testAmbiguousMoveSameRow() {
       LIST.add(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE));
       LIST.add(pieceFact.createPiece(KNIGHT, createNewPosition("f4"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("e6")));
       assertEquals("Nde6", moveBuilder.toString());
   }

   @Test
   void testAmbiguousMoveSameRowAndCol() {
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE));
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("f4"), WHITE));
       LIST.add(pieceFact.createPiece(QUEEN, createNewPosition("d5"), WHITE));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       LIST.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(LIST);

       wrapException(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("e4")));
       assertEquals("Qd4e4", moveBuilder.toString());
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

   private void wrapException(final Move move) {
       try {
           move.build(chessboard);
       } catch (IllegalMoveException e) {
           fail();
       }
   }


}

