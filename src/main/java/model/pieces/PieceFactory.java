package model.pieces;

import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;

/**
 * A standard interface for a Piece Factory.
 */
public interface PieceFactory {
    /**
     * Create a new piece of the given {@link model.piece.utils.Name},
     * {@link model.piece.utils.Position}, and {@link model.piece.utils.Side}.
     *
     * @param name The name of the piece.
     * @param position The position of the piece on the board.
     * @param color The color of the piece.
     * @return A Piece object.
     */
    Piece createPiece(Name name, Position position, Side color);
}
