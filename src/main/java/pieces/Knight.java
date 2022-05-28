package pieces;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import com.fasterxml.jackson.annotation.JsonProperty;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.PieceDirections;
/**
 * A Knight class that extends AbstractPiece abstract class.
 *
 */
public class Knight extends AbstractPiece {

    private static final int KNIGHT_VALUE = 3;
    /**
     * A Knight piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    @ConstructorProperties({"position", "color"})
    protected Knight(final Position position, final Side color) {
        super(Name.KNIGHT, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return Collections
                .unmodifiableList(this.getBasicMoves().singleIteration(PieceDirections.KNIGHT_DIR, board, this));
    }

    @Override
    public int getValue() {
        return KNIGHT_VALUE;
    }

}
