package piece.utils;
/**
 * a POJO to represents a move, so it memorizes the start position and the destination position .
 *
 */
public class Move {
    private final Position start;
    private final Position destination;
    Move(final Position start, final Position destination) {
        this.start = start;
        this.destination = destination;
    }
    /**
     * 
     * @return Position
     */
    public Position getStart() {
        return start;
    }
    /**
     * 
     * @return Position
     */
    public Position getDestination() {
        return destination;
    }
}
