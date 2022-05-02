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
     * @param pos the position you want to move the piece.
     * @param board the current board.
     * @return true if the move is ok, false otherwise.
     */
    boolean move(Position pos, Chessboard board);
    /**
     * 
     * @param piece the piece the user wants to move.
     * @param board the current board.
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
