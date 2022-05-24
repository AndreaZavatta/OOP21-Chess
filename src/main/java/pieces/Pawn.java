package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.ControlsUtility;
import piece.utils.Name;
import piece.utils.PieceDirections;

/**
 * A Pawn class that extends AbstractPiece abstract class.
 *
 */
public final class Pawn extends AbstractPiece {

    private static final int PAWN_VALUE = 1;
    private boolean dontgo;
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
        goFoward(board, list, 1);
        if (!this.isMoved() && !dontgo) {
            goFoward(board, list, 2);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return PAWN_VALUE;
    }

    private void goEat(final Chessboard board, final List<Position> list) {
        //        for (final var pos : PieceDirections.PAWN_DIR.directions()) {
        //            final Position p = ControlsUtility.getNewPosition(this, pos, this.getDirection(this.getColor()));
        //            if (ControlsUtility.checkPosition(this, p, board) 
        //                    && ControlsUtility.checkPiece(this, p, board) 
        //                    && ControlsUtility.checkEnemy(this, p, board)) {
        //                list.add(p);
        //            }
        //        }
        list.addAll(PieceDirections.PAWN_DIR.directions().stream()
                .map(p -> ControlsUtility.getNewPosition(this, p, this.getDirection(this.getSide())))
                .filter(p -> ControlsUtility.checkPosition(this, p, board) 
                        && ControlsUtility.checkPiece(this, p, board) 
                        && ControlsUtility.checkEnemy(this, p, board))
                .collect(Collectors.toUnmodifiableList()));
    }

    private void goFoward(final Chessboard board, final List<Position> list, final int lenght) {
        final Position p = ControlsUtility
                .getNewPosition(this, Position.createNumericPosition(0, lenght), this.getDirection(this.getSide()));
        if (ControlsUtility.checkPosition(this, p, board) 
                && !ControlsUtility.checkPiece(this, p, board)) {
            list.add(p);
        } else {
            dontgo = true;
        }
    }

    private int getDirection(final Side color) {
        return color.equals(Side.BLACK) ? +1 : -1;
    }

}
