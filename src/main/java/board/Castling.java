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
     * @param chessboard - the current chessboard.
     * @param king - the king of the side performing Castling.
     * @param xPos - the
     * @return
     */
    boolean canCastle(Chessboard chessboard, Piece king, int xPos);
}
