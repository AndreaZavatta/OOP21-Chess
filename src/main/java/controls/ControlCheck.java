package controls;

import piece.utils.Color;
import piece.utils.Move;

import java.util.List;
import java.util.function.Consumer;

import piece.utils.Position;
import board.Chessboard;
import pieces.Piece;
/**
 * 
 *
 */
public interface ControlCheck {
    /**
     * It is illegal to move your king into check.
     * this method checks these illegal moves and removes them from the available moves
     * 
     * @param chessboard
     * @param piece
     * @param move
     * @return List<Position>
     */
    List<Position> removeMovesInCheck(Chessboard chessboard, Piece piece, Consumer<Move> move);
    /**
     * this method checks if the king is in check, according to the color.
     * @param chessboard
     * @param color
     * @return boolean
     */
    boolean isInCheck(Chessboard chessboard, Color color);
}
