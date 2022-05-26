package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.Castling;
import board.CastlingImpl;
import board.Chessboard;
import piece.utils.*;

/**
 * A King class that extends AbstractPiece abstract class.
 *
 */
public class King extends AbstractPiece {

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
        final List<Position> list = new ArrayList<>(this.getBasicMoves().singleIteration(PieceDirections.KING_DIR, board, this));
        //castle on left king side
        if (castle.canCastle(board, this, Numbers.ZERO)){
            list.add(castleKingPosition(-Numbers.TWO));
        }
        //castle on right king side
        if(castle.canCastle(board, this, Numbers.SEVEN)){
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
