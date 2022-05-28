package pieces;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import com.fasterxml.jackson.annotation.*;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.PieceDirections;
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
    @ConstructorProperties({"position", "color"})
    protected Rook(final Position position, final Side color) {
        super(Name.ROOK, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return Collections
                .unmodifiableList(this.getBasicMoves().doubleIteration(PieceDirections.ROOK_DIR, board, this));
    }

    @JsonIgnore
    @Override
    public int getValue() {
        return ROOK_VALUE;
    }
}
