package model.pieces;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.board.Chessboard;
import model.move.BasicMoves;
import model.move.BasicMovesImpl;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
/**
 * An abstract class that implements the Piece interface.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class AbstractPiece implements Piece {
    private final Name name;
    private Position position;
    private final Side color;
    @JsonProperty("moved")
    private boolean isMoved;
    @JsonIgnore
    private final BasicMoves basicMoves;
    private static final int HASHCODE_VALUE = 31;
    private static final int HASHCODE_VALUE_2 = 7;

    AbstractPiece(final Name name, final Position position, final Side color) {
        this.name = name;
        this.position = position;
        this.color = color;
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
    public Side getSide() {
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
     * A getter for the BasicMove object needed in each piece.
     * @return the basicMoves
     */
    public BasicMoves getBasicMoves() {
        return basicMoves;
    }

    @Override
    public final int hashCode() {
        var result = color.hashCode() + name.hashCode() * HASHCODE_VALUE + position.hashCode() * HASHCODE_VALUE * HASHCODE_VALUE;
        result = HASHCODE_VALUE_2 / result;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractPiece)) {
            return false;
        }
        final AbstractPiece other = (AbstractPiece) obj;
        return color == other.color && isMoved == other.isMoved && name == other.name
                && Objects.equals(position, other.position);
    }
}
