package pieces;

import java.util.List;
import java.util.Objects;

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
    private Position position;
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
                .removeMovesInCheck(board, this);
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
    public void setPosition(final Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Piece [name=" + name + ", position=" + position + ", color=" + color + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name, position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPiece other = (AbstractPiece) obj;
        return color == other.color && name == other.name && Objects.equals(position, other.position);
    }
}
