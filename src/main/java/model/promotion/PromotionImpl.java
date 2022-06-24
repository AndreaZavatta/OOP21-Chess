package model.promotion;

import java.util.List;
import java.util.Optional;

import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

/**
 * The Pawn promotion class, implements {@link model.promotion.Promotion}.
 */
public class PromotionImpl implements Promotion {

    private final PieceFactory factory = new PieceFactoryImpl();

    @Override
    public Optional<Piece> checkForPromotion(final List<Piece> pieceList) {
        if (checkColor(Side.WHITE, pieceList, 0).isPresent()) {
            return checkColor(Side.WHITE, pieceList, 0);
        }
        return checkColor(Side.BLACK, pieceList, Numbers.SEVEN);
    }

    @Override
    public Piece changePiece(final Name name, final Piece piece) {
        return factory.createPiece(name, piece.getPosition(), piece.getSide());
    }

    private Optional<Piece> checkColor(final Side side, final List<Piece> pieceList, final int position) {
        return pieceList.stream()
                .filter(x -> x.getSide().equals(side))
                .filter(x -> x.getPosition().getY() == position)
                .filter(x -> x.getName().equals(Name.PAWN))
                .findFirst();
    }
}
