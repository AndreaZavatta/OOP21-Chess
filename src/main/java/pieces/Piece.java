package pieces;

import java.util.List;

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
     * @return true if the move is ok, false otherwise.
     */
    boolean move();
    /**
     * 
     * @return a list of positions with all the possible positions the piece can go to.
     */
    List<Position> getAllPossiblePositions();
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

}
