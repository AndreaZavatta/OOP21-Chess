package board;

import piece.utils.Side;
import java.util.List;
import piece.utils.Position;
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
     * @return List<Position>
     */
    List<Position> removeMovesInCheck(Chessboard chessboard, Piece piece);
    /**
     * this method checks if the king is in check, according to the color.
     * @param chessboard
     * @param color
     * @return boolean
     */
    boolean isInCheck(Chessboard chessboard, Side color);
}
