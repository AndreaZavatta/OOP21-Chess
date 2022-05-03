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
 * A Rook class that extends AbstractPiece abstract class.
 *
 */
public class Rook extends AbstractPiece {

    private static final int ROOK_VALUE = 5;

    /**
     * A Rook piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Rook(final Position position, final Color color) {
        super(Name.ROOK, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        for (final var pos : Name.ROOK.directions()) {
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
        return ROOK_VALUE;
    }
}
