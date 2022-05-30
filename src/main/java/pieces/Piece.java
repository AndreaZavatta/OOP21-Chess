package pieces;

import java.util.List;

import board.Chessboard;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import piece.utils.Side;
import piece.utils.Name;
import piece.utils.Position;

/**
 * A standard interface for a Piece object.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = King.class, name = "King"),
        @JsonSubTypes.Type(value = Bishop.class, name = "Bishop"),
        @JsonSubTypes.Type(value = Knight.class, name = "Knight"),
        @JsonSubTypes.Type(value = Pawn.class, name = "Pawn"),
        @JsonSubTypes.Type(value = Queen.class, name = "Queen"),
        @JsonSubTypes.Type(value = Rook.class, name = "Rook")
})
public interface Piece {
    /**
     * A standard getter for the name of the piece.
     * @return the name of the piece.
     */
    Name getName();
    /**
     * A getter for a list of all positions the piece can go to.
     * @param board the current board.
     * @return a list of positions with all the possible positions the piece can go to.
     */
    List<Position> getAllPossiblePositions(Chessboard board);
    /**
     * A getter for the position.
     * @return the position of the piece.
     */
    Position getPosition();
    /**
     * A getter for the side (color).
     * @return the side (color) of the piece.
     */
    Side getSide();
    /**
     * A getter for the value of the piece.
     * @return the value of the piece.
     */
    int getValue();
    /**
     * A boolean that indicates if the piece was moved.
     * @return the current state of the isMoved boolean.
     */
    boolean isMoved();
    /**
     * A setter for the position of the piece.
     * @param position the new Position
     */
    void setPosition(Position position);

}
