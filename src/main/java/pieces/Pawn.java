package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.ControlsUtility;
import piece.utils.Name;

/**
 * A Pawn class that extends AbstractPiece abstract class.
 *
 */
public final class Pawn extends AbstractPiece {

    private static final int PAWN_VALUE = 1;

    /**
     * A Pawn piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Pawn(final Position position, final Color color) {
        super(Name.PAWN, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        goEat(board, list);
        goFoward(board, list, 1);
        if (!this.isMoved()) {
            goFoward(board, list, 2);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return PAWN_VALUE;
    }

    private void goEat(final Chessboard board, final List<Position> list) {
        for (final var pos : Name.PAWN.directions()) {
            final Position p = ControlsUtility.getNewPosition(this, pos, this.getDirection(this.getColor()));
            if (ControlsUtility.checkPosition(this, p, board) 
                    && ControlsUtility.checkPiece(this, p, board) 
                    && ControlsUtility.checkEnemy(this, p, board)) {
                list.add(p);
            }
        }
    }

    private void goFoward(final Chessboard board, final List<Position> list, final int lenght) {
        final Position p = ControlsUtility.getNewPosition(this, 
                new Position(lenght, 0), this.getDirection(this.getColor()));
        if (ControlsUtility.checkPosition(this, p, board) 
                    && !ControlsUtility.checkPiece(this, p, board)) {
            list.add(p);
        }
    }

    private int getDirection(final Color color) {
        if (color.equals(Color.BLACK)) {
            return +1;
        }
        return -1;
    }

}
