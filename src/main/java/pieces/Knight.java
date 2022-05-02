package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.ControlsUtility;
import piece.utils.Name;

/**
 * 
 *
 */
public final class Knight extends AbstractPiece {

    private static final int KNIGHT_VALUE = 3;

    /**
     * A Knight piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Knight(final Position position, final Color color) {
        super(Name.KNIGHT, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        for (final var pos: Name.KNIGHT.directions()) {
            final Position p = ControlsUtility.getNewPosition(this, pos, 1);
            if (ControlsUtility.checkPosition(this, p, board)) {
                if (ControlsUtility.checkPiece(this, p, board)) {
                    if (ControlsUtility.checkEnemy(this, p, board)) {
                        list.add(p);
                    }
                } else {
                    list.add(p);
                }
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return KNIGHT_VALUE;
    }

}
