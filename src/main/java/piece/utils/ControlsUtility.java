package piece.utils;

import board.Chessboard;
import pieces.Piece;

/**
 * A piece utility class.
 *
 */
public final class ControlsUtility {

    private ControlsUtility() {
    }

    /**
     * This method checks if in the given position there is an enemy piece.
     * @param piece the piece you are calculating the possible positions.
     * @param position the position you are checking.
     * @param board the current board.
     * @return true if the position in occupied by an enemy, false otherwise.
     */
    public static boolean checkEnemy(final Piece piece, final Position position, final Chessboard board) {
        return board.getPieceOnPosition(position).map(p -> !p.getColor().equals(piece.getColor())).orElse(false);
    }

    /**
     * This method checks if in the given position there is a piece.
     * @param piece the piece you are calculating the possible positions.
     * @param position the position you are checking.
     * @param board the current board.
     * @return true if there is a piece in the specified position, false otherwise.
     */
    public static boolean checkPiece(final Piece piece, final Position position, final Chessboard board) {
        return board.getPieceOnPosition(position).isPresent();
    }

    /**
     * This method checks if the given position is in the board.
     * @param board the current board.
     * @param piece the piece you are calculating the possible positions.
     * @param position the position you are checking.
     * @return true if the position is in the board, false otherwise.
     */
    public static boolean checkPosition(final Piece piece, final Position position, final Chessboard board) {
        return position.getX() < 8 && position.getX() >= 0 
                && position.getY() < 8 && position.getY() >= 0;
    }

    /**
     * This method, given a direction and a multiplier, returns a new position.
     * @param piece the piece you are calculating the possible positions.
     * @param direction the directions the piece can go to.
     * @param multiplier position multiplier.
     * @return the new position.
     */
    public static Position getNewPosition(final Piece piece, final Position direction, final int multiplier) {
        return Position.createNumericPosition(piece.getPosition().getX() + (direction.getX() * multiplier),
                piece.getPosition().getY() + (direction.getY() * multiplier));
    }
}
