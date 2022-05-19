package promotion;

import java.util.List;

import piece.utils.Name;
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
        return checkColor(Side.BLACK, pieceList, 0) || checkColor(Side.WHITE, pieceList, 7);
    }
    /**
     * 
     * @return a
     */
    public Piece changePiece() {
        return factory.createPiece(null, null, null);
    }

    private boolean checkColor(final Side side, final List<Piece> pieceList, final int position) {
        return pieceList.stream()
                .filter(x -> x.getColor().equals(side))
                .filter(x -> x.getPosition().getY() == position)
                .filter(x -> x.getName().equals(Name.PAWN))
                .findFirst().isPresent();
    }
}
