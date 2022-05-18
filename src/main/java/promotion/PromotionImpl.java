package promotion;

import java.util.List;

import piece.utils.Side;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

/**
 * The Pawn promotion class.
 *
 */
public class PromotionImpl {

    private final PieceFactory factory = new PieceFactoryImpl();

    /**
     * 
     * @param pieceList
     * @return a
     */
    public boolean checkForPromotion(final List<Piece> pieceList) {
        //        pieceList.stream().filter(x -> x.getColor().equals(Side.WHITE)).
        //        filter(x -> x.getPosition().getY() == 0).findFirst().isPresent();
        return pieceList.stream().filter(x -> x.getColor().equals(Side.WHITE)).
                filter(x -> x.getPosition().getY() == 0).findFirst().isPresent();
    }
}
