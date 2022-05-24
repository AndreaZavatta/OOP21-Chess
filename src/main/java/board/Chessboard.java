package board;

import java.util.List;
import java.util.Optional;

import piece.utils.Name;
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
     */
    void move(Position actualPos, Position finalPos);

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

    /**
     * 
     * @param selectedPos
     * @return an optional of a piece
     */
    Optional<Piece> getPieceOnPosition(Position selectedPos);

    /**
     * 
     * @param attacker
     * @return a list of all possible position
     */
    List<Position> getAllPosition(Piece attacker);

    /**
     * 
     * @param namePiece
     * 
     * pawn get promoted to a new Piece.
     */
    void promotion(Name namePiece);
}
