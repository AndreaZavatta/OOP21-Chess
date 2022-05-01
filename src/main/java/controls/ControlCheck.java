package controls;

import piece.utils.Color;
import java.util.List;

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
     * @param avaliableMoves
     * @return List<Position>
     */
    List<Position> moveInCheck(Chessboard chessboard, Piece piece, List<Position> avaliableMoves);
    /**
     * this method checks if the king is in check, according to the color.
     * @param chessboard
     * @param color
     * @return boolean
     */
    boolean isInCheck(Chessboard chessboard, Color color);
}
