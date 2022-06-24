package model.board;

import model.piece.utils.Side;

/**
 * 
 * This Interface models and handles all the possible ways a chess game can end with.
 *
 */
public interface EndGame {
    /**
     * This method checks if the game has ended with a checkmate.
     *
     * @param chessboard the current chessboard.
     * @param side the attacked side.
     * @return true if the passed Side is under Checkmate.
     */
    boolean isCheckmate(Chessboard chessboard, Side side);

    /**
     * This method checks if the game has ended with a stalemate.
     *
     * @param chessboard the current chessboard.
     * @param side the attacked side.
     * @return true if the passed Side cannot move anything.
     */
    boolean isStalemate(Chessboard chessboard, Side side);

    /**
     * This method checks if the game has ended with a draw, due to lack of chess pieces.
     * @param chessboard the current chessboard.
     * @return true if the match ends in Stalemate due to insufficiency of material.
     */
    boolean isDrawByInsufficientMaterial(Chessboard chessboard);

    /**
     * @param chessboard
     * @return true if the match ends in Stalemate due to moves repetition.
     */
    /* boolean isDrawByRepetition(Chessboard chessboard); */

    /**
     * This method returns if the current game ended with a draw.
     * @param chessboard the current chessboard.
     * @param side the attacked side.
     * @return true if the match ends with a draw.
     */
    boolean isDraw(Chessboard chessboard, Side side);
}
