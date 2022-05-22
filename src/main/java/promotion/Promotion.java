package promotion;

import java.util.List;
import java.util.Optional;

import piece.utils.Name;
import pieces.Piece;
/**
 * An interface for the pawn's promotion.
 *
 */
public interface Promotion {

    /**
     * This method checks if there is a pawn on the highest or lowest row 
     * of the board.
     * 
     * @param pieceList the current list of pieces
     * @return an Optional of piece
     */
    Optional<Piece> checkForPromotion(List<Piece> pieceList);

    /**
     * This method is used to create a new piece. 
     * 
     * @param name the name of the piece you will create
     * @param piece the old piece you get the position and side from
     * @return a new piece with the position and side from the old piece
     */
    Piece changePiece(Name name, Piece piece);

}
