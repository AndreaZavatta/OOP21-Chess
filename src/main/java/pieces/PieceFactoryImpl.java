package pieces;

import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;
/**
 * Implementation of PieceFactory interface.
 *
 */
public class PieceFactoryImpl implements PieceFactory {

    @Override
    public Piece createPiece(final Name name, final Position position, final Color color) {
        switch (name) {
        case QUEEN: 
            return new Queen(position, color);
        case KNIGHT: 
            return new Knight(position, color);
        case PAWN: 
            return new Pawn(position, color);
        case KING: 
            return new King(position, color);
        case ROOK: 
            return new Rook(position, color);
        case BISHOP: 
            return new Bishop(position, color);
        default: return null;
        }
    }
}
