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
        return Collections
                .unmodifiableList(this.getBasicMoves().singleIteration(PieceDirections.KING_DIR, board, this));
    }

    @Override
    public int getValue() {
        return KING_VALUE;
    }

}
