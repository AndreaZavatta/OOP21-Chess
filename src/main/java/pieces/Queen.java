package pieces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.PieceDirections;

/**
 * A Queen class that extends AbstractPiece abstract class.
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
    protected Queen(final Position position, final Side color) {
        super(Name.QUEEN, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return Collections.
                unmodifiableList(this.getBasicMoves().doubleIteration(PieceDirections.QUEEN_DIR, board, this));
    }

    @Override
    public int getValue() {
        return QUEEN_VALUE;
    }


}
