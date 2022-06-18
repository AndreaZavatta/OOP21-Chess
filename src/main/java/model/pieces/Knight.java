package model.pieces;

import java.util.Collections;
import java.util.List;

import model.board.Chessboard;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.PieceDirections;

/**
 * A Knight class that extends AbstractPiece abstract class.
 */
public class Knight extends AbstractPiece {

    private static final int KNIGHT_VALUE = 3;
    /**
     * A Knight piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */

    protected Knight(final Position position, final Side color) {
        super(Name.KNIGHT, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return Collections
                .unmodifiableList(this.getBasicMoves().directMove(PieceDirections.KNIGHT_DIR, board, this));
    }

    @Override
    public int getValue() {
        return KNIGHT_VALUE;
    }
}
