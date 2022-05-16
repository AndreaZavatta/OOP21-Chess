package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.ControlsUtility;
import piece.utils.Name;
/**
 * A Bishop class that extends AbstractPiece abstract class.
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
    protected Bishop(final Position position, final Side color) {
        super(Name.BISHOP, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        for (final var pos : Name.BISHOP.directions()) {
            for (int i = 1; i < 8; i++) {
                final Position p = ControlsUtility.getNewPosition(this, pos, i);
                if (ControlsUtility.checkPiece(this, p, board)) {
                    if (ControlsUtility.checkEnemy(this, p, board)) {
                        list.add(p);
                    } 
                    break;
                } else if (ControlsUtility.checkPosition(this, p, board)) {
                    list.add(p);
                } else {
                    break;
                }
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return BISHOP_VALUE;
    }
}
