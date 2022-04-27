package pieces;

import java.util.List;

import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;

/**
 * 
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
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean move() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Position> getAllPossiblePositions() {
        // TODO Auto-generated method stub
        return List.of();
    }

    @Override
    public int getValue() {
        // TODO Auto-generated method stub
        return PAWN_VALUE;
    }

}
