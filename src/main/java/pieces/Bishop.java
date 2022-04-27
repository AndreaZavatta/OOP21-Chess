package pieces;

import java.util.List;

import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;
/**
 * 
 *
 */
public class Bishop extends AbstractPiece {

    private static final int BISHOP_VALUE = 3;

    /**
     * A Bishop piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Bishop(final Position position, final Color color) {
        super(Name.BISHOP, position, color);
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
        return BISHOP_VALUE;
    }
}
