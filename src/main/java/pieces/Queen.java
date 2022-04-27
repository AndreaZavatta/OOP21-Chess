package pieces;

import java.util.List;

import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;

/**
 * 
 *
 */
public class Queen extends AbstractPiece {

    private static final int QUEEN_VALUE = 9;

    /**
     * A Queen piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Queen(final Position position, final Color color) {
        super(Name.QUEEN, position, color);
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
        return QUEEN_VALUE;
    }

}
