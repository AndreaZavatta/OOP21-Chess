package model.pieces;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.board.Chessboard;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.PieceDirections;
/**
 * A Rook class that extends {@link model.pieces.AbstractPiece} abstract class.
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
    protected Rook(final Position position, final Side color) {
        super(Name.ROOK, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        return Collections
                .unmodifiableList(this.getPieceMovement().multipleMove(PieceDirections.ROOK_DIR, board, this));
    }

    @JsonIgnore
    @Override
    public int getValue() {
        return ROOK_VALUE;
    }
}
