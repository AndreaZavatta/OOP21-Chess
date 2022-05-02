package board;

import java.util.List;
import piece.utils.Position;
import pieces.Piece;

    /**
     * Interface for a simple chessboard. 
     *
     */
public interface Chessboard {

    /**
     * 
     * @return a list of all pieces on the board
     */
    List<Piece> getAllPieces();

    /**
     * 
     * @param actualPos
     * @param finalPos
     * @return if the piece can do this move
     */
    boolean move(Position actualPos, Position finalPos);

    /**
     * 
     * @return X border of chessboard
     */
    int getxBorder();

    /**
     * 
     * @return Y border of chessboard
     */
    int getyBorder();
}
