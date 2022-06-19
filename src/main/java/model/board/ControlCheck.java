package model.board;

import model.piece.utils.Side;
import java.util.List;
import model.piece.utils.Position;
import model.pieces.Piece;
/**
 * this interface makes controls if the king is in check
 * and controls if the positions the king is moving to are attacked by other pieces
 *
 */
public interface ControlCheck {
    /**
     * It is illegal to move your king into check.
     * this method checks these illegal moves and removes them from the available moves
     * 
     * @param chessboard The chessboard where the moves must be controlled
     * @param piece the piece to which have to check the moves
     * @return List<Position>
     */
    List<Position> controlledMoves(Chessboard chessboard, Piece piece);
    /**
     * this method checks if the king is in check according to the color, without checking whether the opposing king is attacking the king in question.
     * @param chessboard The chessboard in which to check whether the king is in check
     * @param color The color of the king to check if it is in check
     * @return boolean
     */
    boolean isInCheckWithoutKing(Chessboard chessboard, Side color);
    /**
     * this method checks if the king is in check, according to the color.
     * @param chessboard The chessboard in which to check whether the king is in check
     * @param color The color of the king to check if it is in check
     * @return boolean
     */
    boolean isInCheck(Chessboard chessboard, Side color);
}
