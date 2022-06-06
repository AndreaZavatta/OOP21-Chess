package board;

import model.pieces.Piece;

/**
 *
 * This interface models and handles all Castling controls.
 *
 */
public interface Castling {

    /**
     * 
     * @param chessboard
     * @param king
     * @param xPos
     * @return
     */
    boolean canCastle(Chessboard chessboard, Piece king, int xPos);
}
