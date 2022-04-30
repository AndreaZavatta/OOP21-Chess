package pieces;

import java.util.List;

import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;
/**
 * 
 *
 */
public abstract class AbstractPiece implements Piece {
    private final Name name;
    private final Position position;
    private final Color color;

    AbstractPiece(final Name name, final Position position, final Color color) {
        this.name = name;
        this.position = position;
        this.color = color;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public abstract boolean move();

    @Override
    public abstract List<Position> getAllPossiblePositions();

    @Override
    public abstract int getValue();

}
