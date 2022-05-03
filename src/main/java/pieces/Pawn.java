package pieces;

import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;

/**
 * A Pawn class that extends AbstractPiece abstract class.
 *
 */
public final class Pawn extends AbstractPiece {

    private static final int PAWN_VALUE = 1;

    /**
     * A Pawn piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Pawn(final Position position, final Color color) {
        super(Name.PAWN, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return List.of();
    }

    @Override
    public int getValue() {
        return PAWN_VALUE;
    }

}
