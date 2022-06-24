package model.promotion;

import java.util.List;
import java.util.Optional;

import model.piece.utils.Name;
import model.pieces.Piece;
/**
 * An interface for the pawn's promotion.
 */
public interface Promotion {

    /**
     * If the last piece in the list is a pawn, and it's at the end of the board,
     * return it, otherwise return null.
     *
     * @param pieceList The list of pieces on the board.
     * @return Optional of {@link Piece Piece.class}
     */
    Optional<Piece> checkForPromotion(List<Piece> pieceList);


    /**
     * Change the piece with the given name to the given piece.
     *
     * @param name The name of the piece you want to change.
     * @param piece The piece to be changed.
     * @return The piece that was replaced.
     */
    Piece changePiece(Name name, Piece piece);
}
