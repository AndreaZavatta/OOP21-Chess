package board;

import piece.utils.Side;


/**
 * 
 * 
 *
 */
public interface EndGame {

    /**
     * 
     * @return
     */
    boolean isGameOver();

    /**
     * 
     * @param side
     * @param chessboard
     * @return
     */
    boolean isCheckmate(Side side, Chessboard chessboard);

    /**
     * 
     * @param side
     * @param chessboard
     * @return
     */
    boolean isStalemate(Side side, Chessboard chessboard);

    /**
     * 
     * @param chessboard
     * @return
     */
    boolean isDrawByInsufficientfMaterial(Chessboard chessboard);

}