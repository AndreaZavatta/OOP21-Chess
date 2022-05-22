package promotion;

import java.util.List;
import java.util.Optional;

import piece.utils.Name;
import pieces.Piece;

public interface Promotion {

    /**
     * 
     * @param pieceList
     * @return a
     */
    Optional<Piece> checkForPromotion(List<Piece> pieceList);

    /**
     * 
     * @param name
     * @param piece
     * @return a
     */
    Piece changePiece(Name name, Piece piece);

}