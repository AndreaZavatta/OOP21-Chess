package board;

import piece.utils.Side;


/**
 * 
 * This Interface models and handles all the possible ways a chess game can end with.
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
     * @return true if the passed Side is under Checkmate.
     */
    boolean isCheckmate(Side side, Chessboard chessboard);

    /**
     * 
     * @param side
     * @param chessboard
     * @return true if the passed Side cannot move anything.
     */
    boolean isStalemate(Side side, Chessboard chessboard);

    /**
     * 
     * @param chessboard
     * @return true if the match ends in Stalemate due to insufficiency of material.
     */
    boolean isDrawByInsufficientfMaterial(Chessboard chessboard);

    /**
     * 
     * @param chessboard
     * @return true if the match ends in Stalemate due to moves repetition.
     */
    boolean isDrawByRepetition(Chessboard chessboard);

}
