package pieces;

import java.util.List;

import board.Chessboard;
import controls.ControlCheck;
import controls.ControlCheckImpl;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;
/**
 * An abstract class that implements the Piece interface.
 *
 */
public abstract class AbstractPiece implements Piece {
    private final Name name;
    private final Position position;
    private final Color color;
    private final ControlCheck advancedControls;
    private boolean isMoved;

    AbstractPiece(final Name name, final Position position, final Color color) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.advancedControls = new ControlCheckImpl();
        this.isMoved = false;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public boolean canMove(final Position pos, final Chessboard board) {
        final List<Position> l = this.getAdvancedControls()
                .removeMoveInCheck(board, this, this.getAllPossiblePositions(board));
        return l.contains(pos);
    }

    @Override
    public abstract List<Position> getAllPossiblePositions(Chessboard board);

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public abstract int getValue();

    @Override
    public ControlCheck getAdvancedControls() {
        return this.advancedControls;
    }

    @Override
    public void setIsMoved() {
        this.isMoved = true;
    }

    @Override
    public boolean isMoved() {
        return this.isMoved;
    }

    @Override
    public String toString() {
        return "Piece [name=" + name + ", position=" + position + ", color=" + color + "]";
    }
}
