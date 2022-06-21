package model.piece.utils;

import model.board.Chessboard;
import model.pieces.Piece;

/**
 * A {@link model.pieces.Piece} utility class.
 */
public final class ControlsUtility {

    private ControlsUtility() {
    }
    /**
     * This method checks if in the given {@link model.piece.utils.Position} there is an
     * enemy {@link model.pieces.Piece}.
     * @param piece the piece you are calculating the possible positions.
     * @param position the position you are checking.
     * @param board the current board.
     * @return true if the position in occupied by an enemy, false otherwise.
     */
    public static boolean checkEnemyOnPosition(final Piece piece, final Position position, final Chessboard board) {
        return board.getPieceOnPosition(position).map(p -> !p.getSide().equals(piece.getSide())).orElse(false);
    }
    /**
     * Check if there is a {@link model.pieces.Piece} on the given {@link model.piece.utils.Position}
     * on the given {@link model.board.Chessboard}.
     * @param position The position to check.
     * @param board The chessboard to check.
     * @return true if there is a piece in the specified position, false otherwise.
     */
    public static boolean checkPieceOnPosition(final Position position, final Chessboard board) {
        return board.getPieceOnPosition(position).isPresent();
    }
    /**
     * Check if the {@link model.piece.utils.Position} is on the board.
     * @param position The position to check.
     * @return true if the position is in the board, false otherwise.
     */
    public static boolean checkPositionOnBoard(final Position position) {
        return position.getX() < 8 && position.getX() >= 0 
                && position.getY() < 8 && position.getY() >= 0;
    }
    /**
     * Given a {@link model.pieces.Piece}, a {@link model.piece.utils.PieceDirections}, and a multiplier,
     * return a new {@link model.piece.utils.Position}.
     * @param piece The piece that is moving
     * @param direction The direction in which the piece is moving.
     * @param multiplier This is the number of steps the piece will take in the direction specified.
     * @return A new position
     */
    public static Position getNewPosition(final Piece piece, final Position direction, final int multiplier) {
        return Position.createNumericPosition(piece.getPosition().getX() + (direction.getX() * multiplier),
                piece.getPosition().getY() + (direction.getY() * multiplier));
    }
}
