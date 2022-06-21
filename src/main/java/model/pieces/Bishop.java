package model.pieces;
import java.util.Collections;
import java.util.List;
import model.board.Chessboard;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.PieceDirections;
/**
 * A Bishop class that extends {@link model.pieces.AbstractPiece} abstract class.
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
                .unmodifiableList(this.getBasicMoves().iteratedMove(PieceDirections.BISHOP_DIR, board, this));
    }

    @Override
    public int getValue() {
        return BISHOP_VALUE;
    }
}
