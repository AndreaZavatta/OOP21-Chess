package model.pieces;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.move.PieceMovement;
import model.move.PieceMovementImpl;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;
/**
 * An abstract class that implements the {@link model.pieces.Piece} interface.
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
    private final PieceMovement pieceMovement;

    AbstractPiece(final Name name, final Position position, final Side color) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.pieceMovement = new PieceMovementImpl();
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
    public Side getSide() {
        return this.color;
    }

    @Override
    public boolean isMoved() {
        return this.isMoved;
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;
        this.isMoved = true;
    }

    @Override
    public PieceMovement getPieceMovement() {
        return pieceMovement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
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
        return color == other.color && name == other.name && Objects.equals(position, other.position);
    }

}
