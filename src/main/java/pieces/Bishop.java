package pieces;

import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.PieceDirections;
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
        return Collections
                .unmodifiableList(this.getBasicMoves().doubleIteration(PieceDirections.BISHOP_DIR, board, this));
    }

    @Override
    public int getValue() {
        return BISHOP_VALUE;
    }
}
