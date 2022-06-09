package model.pieces;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import board.Chessboard;
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
    public String toString() {
        return "Piece [name=" + name + ", position=" + position + ", color=" + color + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + ((color == null) ? 3 : color.hashCode());
        result = prime * result + position.hashCode();
        result = prime * result + ((name == null) ? 3 : name.hashCode());
        return result;
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
