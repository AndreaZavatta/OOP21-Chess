package piece.utils;

import board.Chessboard;

/**
 * a POJO to represents a move, so it memorizes the start position and the destination position .
 *
 */
public class Move {
    private final Position start;
    private final Position destination;
    private final Chessboard chessboard;
    /**
     * @param chessboard
     * @param start
     * @param destination
     */
    public Move(final Position start, final Position destination, final Chessboard chessboard) {
        this.start = start;
        this.destination = destination;
        this.chessboard = chessboard;
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
    /**
     * 
     * @return chessboard
     */
    public Chessboard getChessboard() {
        return chessboard;
    }
}
