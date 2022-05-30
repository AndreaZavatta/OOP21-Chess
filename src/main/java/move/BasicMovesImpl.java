package move;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import board.Chessboard;
import piece.utils.ControlsUtility;
import piece.utils.PieceDirections;
import piece.utils.Position;
import pieces.Piece;
/**
 * Implementation of BasicMoves Interface.
 */
public class BasicMovesImpl implements BasicMoves {

    @Override
    public List<Position> iteratedMove(final PieceDirections directions, final Chessboard board,
                                       final Piece piece) {
        return directions.getDirections().stream()
                .flatMap(d -> 
                    IntStream.range(1, 8)
                    .takeWhile(i -> isMovementValid(piece, i, d, board))
                    .mapToObj(i -> ControlsUtility.getNewPosition(piece, d, i)))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Position> directMove(final PieceDirections directions, final Chessboard board,
                                     final Piece piece) {
        return directions.getDirections().stream()
                .map(p -> ControlsUtility.getNewPosition(piece, p, 1))
                .filter(ControlsUtility::checkPositionOnBoard)
                .filter(p -> !ControlsUtility.checkPieceOnPosition(p, board) || ControlsUtility.checkEnemyOnPosition(piece, p, board))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isMovementValid(final Piece piece, final int length, final Position direction,
                                    final Chessboard board) {
        final Position p = ControlsUtility.getNewPosition(piece, direction, length);
        if (!ControlsUtility.checkPositionOnBoard(p)) {
            return false;
        }
        final Position previousPosition = ControlsUtility.getNewPosition(piece, direction, length - 1);

        if (ControlsUtility.checkEnemyOnPosition(piece, previousPosition, board)) {
            return false;
        }
        return !(ControlsUtility.checkPieceOnPosition(p, board) && !ControlsUtility.checkEnemyOnPosition(piece, p, board));
    }
}
