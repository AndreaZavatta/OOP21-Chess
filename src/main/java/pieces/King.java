package pieces;

import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.PieceDirections;
/**
 * A King class that extends AbstractPiece abstract class.
 *
 */
public class King extends AbstractPiece {

    private static final int KING_VALUE = 0;
    /**
     * A King piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected King(final Position position, final Side color) {
        super(Name.KING, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
//        final List<Position> list = new ArrayList<>();
//        for (final var pos : PieceDirections.KING_DIR.directions()) {
//            final Position p = ControlsUtility.getNewPosition(this, pos, 1);
//            if (ControlsUtility.checkPosition(this, p, board)) {
//                if (ControlsUtility.checkPiece(this, p, board)) {
//                    if (ControlsUtility.checkEnemy(this, p, board)) {
//                        list.add(p);
//                    }
//                } else {
//                    list.add(p);
//                }
//            }
//        }
//        return Collections.unmodifiableList(list);
        return Collections.unmodifiableList(this.getBasicMoves().singleIteration(PieceDirections.KING_DIR, board, this));
    }

    @Override
    public int getValue() {
        return KING_VALUE;
    }

}
