package chessparsers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static model.piece.utils.Position.createNewPosition;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import chess.parsers.Move;
import chess.parsers.MoveBuilder;
import exceptions.IllegalMoveException;
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
   private static final List<Piece> List = new ArrayList<>();

   /**
    * 
    */

   @Test
   void testPawnAdvancementMove() {
       List.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("d4")));
       assertEquals("d4", moveBuilder.toString());
   }

   @Test
   void testPromotionMove() {
       List.add(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("e3"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("b7"), WHITE))
                 .promotion(QUEEN)
                 .destination(createNewPosition("b8")));
       assertEquals("b8=Q", moveBuilder.toString());
   }

   @Test
   void testDrawOffer() {
       wrapBuild(moveBuilder.drawOffer());
       assertEquals("(=)", moveBuilder.toString());
   }
   @Test
   void testCheckMove() {
       List.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("e3"), WHITE))
                 .check()
                 .destination(createNewPosition("b3")));
       assertEquals("Qb3+", moveBuilder.toString());
   }

   @Test
   void testCheckmate() {
       List.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE));
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("a4"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("a8"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

           wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("c4"), WHITE))
                    .checkmate()
                    .destination(createNewPosition("b3")));
       assertEquals("Qcb3#", moveBuilder.toString());
   }

   @Test
   void testPawnCaptureMove() {
       List.add(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK));
       List.add(pieceFact.createPiece(PAWN, createNewPosition("e3"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(PAWN, createNewPosition("d4"), BLACK))
                 .capture()
                 .destination(createNewPosition("e3")));
       assertEquals("dxe3", moveBuilder.toString());
   }
   @Test
   void testKingSideCastling() {
       wrapBuild(moveBuilder.kingSideCastling());
       assertEquals("0-0", moveBuilder.toString());
   }
   @Test
   void testQueenSideCastling() {
       wrapBuild(moveBuilder.queenSideCastling());
       assertEquals("0-0-0", moveBuilder.toString());
   }
   @Test
   void testBishopMove() {
       List.add(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE));
       List.add(pieceFact.createPiece(BISHOP, createNewPosition("e5"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(BISHOP, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("f2")));
       assertEquals("Bf2", moveBuilder.toString());
   }

   @Test
   void testAmbiguousMoveSameRow() {
       List.add(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE));
       List.add(pieceFact.createPiece(KNIGHT, createNewPosition("f4"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(KNIGHT, createNewPosition("d4"), WHITE))
               .destination(createNewPosition("e6")));
       assertEquals("Nde6", moveBuilder.toString());
   }

   @Test
   void testAmbiguousMoveSameRowAndCol() {
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE));
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("f4"), WHITE));
       List.add(pieceFact.createPiece(QUEEN, createNewPosition("d5"), WHITE));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("b7"), BLACK));
       List.add(pieceFact.createPiece(Name.KING, createNewPosition("g2"), WHITE));
       chessboard = boardFactory.createTestCB(List);

       wrapBuild(moveBuilder.piece(pieceFact.createPiece(QUEEN, createNewPosition("d4"), WHITE))
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

   private void wrapBuild(final Move move) {
       try {
           move.build(chessboard);
       } catch (IllegalMoveException e) {
           fail();
       }
   }


}

