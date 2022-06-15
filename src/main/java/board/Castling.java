package board;

import model.pieces.Piece;

/**
 *
 * This interface models and handles all Castling controls.
 *
 */
public interface Castling {
    /**
     * This method indicates if it is possible - for the referenced king - to perform castling.
     * @param chessboard the current chessboard.
     * @param king the king of the side performing Castling.
     * @param xRook  the x coordinate of the rook; it also indicates the side of the castling (long/short).
     * @return true if it is possible to castle.
     */ 
    boolean canCastle(Chessboard chessboard, Piece king, int xRook);
}
