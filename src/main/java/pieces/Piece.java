package pieces;

import java.util.List;

import board.Chessboard;
import controls.ControlCheck;
import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;

/**
 * A standard interface for a Piece object.
 *
 */
public interface Piece {
    /**
     * 
     * @return the name of the piece.
     */
    Name getName();
    /**
     * 
     * @param pos the position you want to move the piece.
     * @param board the current board.
     * @return true if the move is ok, false otherwise.
     */
    boolean canMove(Position pos, Chessboard board);
    /**
     * 
     * @param board the current board.
     * @return a list of positions with all the possible positions the piece can go to.
     */
    List<Position> getAllPossiblePositions(Chessboard board);
    /**
     * 
     * @return the position of the piece.
     */
    Position getPosition();
    /**
     * 
     * @return the color of the piece.
     */
    Color getColor();
    /**
     * 
     * @return the value of the piece.
     */
    int getValue();
    /**
     * 
     * @return a ControlCheck object.
     */
    ControlCheck getAdvancedControls();
    /**
     * Set to true the isMoved boolean.
     * 
     */
    void setIsMoved();
    /**
     * 
     * @return the current state of the isMoved boolean.
     */
    boolean isMoved();

}
