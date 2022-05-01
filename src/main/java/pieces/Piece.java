package pieces;

import java.util.List;

import board.Chessboard;
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
     * @param pos TODO
     * @param board TODO
     * @return true if the move is ok, false otherwise.
     */
    boolean move(Position pos, Chessboard board);
    /**
     * 
     * @param piece TODO
     * @param board TODO
     * @return a list of positions with all the possible positions the piece can go to.
     */
    List<Position> getAllPossiblePositions(Piece piece, Chessboard board);
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
