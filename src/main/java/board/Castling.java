package board;

import pieces.Piece;

/**
 * This interface handles all Castling opportunities.
 */
public interface Castling {

   /**
    * 
    * @param chessboard
    * @param king
    * @return true when it is possible to Castle on the left side of the king
    */
    boolean canCastleLeft(Chessboard chessboard, Piece king);

    /**
     * 
     * @param chessboard
     * @param king
     * @return true when it is possible to Castle on the right side of the king
     */
    boolean canCastleRight(Chessboard chessboard, Piece king);

}
