package model.pieces;

import java.util.Collections;
import java.util.List;

import model.board.Chessboard;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.PieceDirections;

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
                unmodifiableList(this.getBasicMoves().iteratedMove(PieceDirections.QUEEN_DIR, board, this));
    }

    @Override
    public int getValue() {
        return QUEEN_VALUE;
    }
}
