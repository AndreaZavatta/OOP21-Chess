package board;

import pieces.Piece;

/**
 * 
 * This interface handles all Castling opportunities
 * 
 */
public interface Castling {

    boolean canCastle(Chessboard chessboard, Piece king, int xPos);

}