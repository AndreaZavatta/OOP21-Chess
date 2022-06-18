package model.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.board.Castling;
import model.board.CastlingImpl;
import model.board.Chessboard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.PieceDirections;
import model.piece.utils.Position;
import model.piece.utils.Side;

/**
 * A King class that extends AbstractPiece abstract class.
 */
public class King extends AbstractPiece {

    @JsonIgnore
    private final Castling castle = new CastlingImpl();
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
        final List<Position> list =
                new ArrayList<>(this.getBasicMoves().directMove(PieceDirections.KING_DIR, board, this));
        if (castle.canCastle(board, this, Numbers.ZERO)) {
            list.add(castleKingPosition(-Numbers.TWO));
        }
        if (castle.canCastle(board, this, Numbers.SEVEN)) {
            list.add(castleKingPosition(Numbers.TWO));
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return KING_VALUE;
    }

    private Position castleKingPosition(final int direction) {
        return Position.createNumericPosition(this.getPosition().getX() + direction, this.getPosition().getY());
    }
}
