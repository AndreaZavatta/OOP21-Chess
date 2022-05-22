package promotion;

import java.util.List;
import java.util.Optional;

import piece.utils.Name;
import piece.utils.Side;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

/**
 * The Pawn promotion class.
 *
 */
public class PromotionImpl implements Promotion {

    private final PieceFactory factory = new PieceFactoryImpl();

    /**
     * 
     * @param pieceList
     * @return a
     */
    @Override
    public Optional<Piece> checkForPromotion(final List<Piece> pieceList) {
        if (checkColor(Side.BLACK, pieceList, 0).isPresent()) {
            return checkColor(Side.BLACK, pieceList, 0);
        }
        return Optional.empty();
    }
    /**
     * 
     * @param name
     * @param piece
     * @return a
     */
    @Override
    public Piece changePiece(final Name name, final Piece piece) {
        return factory.createPiece(name, piece.getPosition(), piece.getColor());
    }

    private Optional<Piece> checkColor(final Side side, final List<Piece> pieceList, final int position) {
        return pieceList.stream()
                .filter(x -> x.getColor().equals(side))
                .filter(x -> x.getPosition().getY() == position)
                .filter(x -> x.getName().equals(Name.PAWN))
                .findFirst();
    }
}
