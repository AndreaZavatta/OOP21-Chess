package model.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import board.Chessboard;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.ControlsUtility;
import model.piece.utils.Name;
import model.piece.utils.PieceDirections;

/**
 * A Pawn class that extends AbstractPiece abstract class.
 */
public class Pawn extends AbstractPiece {

    private static final int PAWN_VALUE = 1;
    private boolean dontGo;
    /**
     * A Pawn piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Pawn(final Position position, final Side color) {
        super(Name.PAWN, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        goEat(board, list);
        goForward(board, list, 1);
        if (!this.isMoved() && !dontGo) {
            goForward(board, list, 2);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return PAWN_VALUE;
    }

    private void goEat(final Chessboard board, final List<Position> list) {
        list.addAll(PieceDirections.PAWN_DIR.getDirections().stream()
                .map(p -> ControlsUtility.getNewPosition(this, p, this.getDirection(this.getSide())))
                .filter(p -> ControlsUtility.checkPositionOnBoard(p)
                        && ControlsUtility.checkPieceOnPosition(p, board)
                        && ControlsUtility.checkEnemyOnPosition(this, p, board))
                .collect(Collectors.toUnmodifiableList()));
    }

    private void goForward(final Chessboard board, final List<Position> list, final int length) {
        final Position p = ControlsUtility
                .getNewPosition(this, Position.createNumericPosition(0, length),
                        this.getDirection(this.getSide()));
        if (ControlsUtility.checkPositionOnBoard(p)
                && !ControlsUtility.checkPieceOnPosition(p, board)) {
            list.add(p);
        } else {
            dontGo = true;
        }
    }

    private int getDirection(final Side color) {
        return color.equals(Side.BLACK) ? +1 : -1;
    }
}
