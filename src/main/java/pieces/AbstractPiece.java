package pieces;

import java.util.List;
import java.util.Objects;

import board.Chessboard;
import move.BasicMoves;
import move.BasicMovesImpl;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;
/**
 * An abstract class that implements the Piece interface.
 *
 */
public abstract class AbstractPiece implements Piece {
    private final Name name;
    private Position position;
    private final Side color;
    private boolean isMoved;
    private final BasicMoves basicMoves;

    AbstractPiece(final Name name, final Position position, final Side color) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.isMoved = false;
        this.basicMoves = new BasicMovesImpl();
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public abstract List<Position> getAllPossiblePositions(Chessboard board);

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Side getColor() {
        return this.color;
    }

    @Override
    public abstract int getValue();

    @Override
    public boolean isMoved() {
        return this.isMoved;
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;
        this.isMoved = true;
    }

    /**
     * @return the basicMoves
     */
    public BasicMoves getBasicMoves() {
        return basicMoves;
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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractPiece other = (AbstractPiece) obj;
        return color == other.color && name == other.name && Objects.equals(position, other.position);
    }
}
