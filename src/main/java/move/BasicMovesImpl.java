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
 *
 */
public class BasicMovesImpl implements BasicMoves {

    @Override
    public List<Position> doubleIteration(final PieceDirections directions, final Chessboard board, final Piece piece) {
        return directions.directions().stream()
                .flatMap(d -> 
                    IntStream.range(1, 8)
                    .takeWhile(i -> isMovementValid(piece, i, d, board))
                    .mapToObj(i -> ControlsUtility.getNewPosition(piece, d, i)))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Position> singleIteration(final PieceDirections directions, final Chessboard board, final Piece piece) {
        return directions.directions().stream()
                .map(p -> ControlsUtility.getNewPosition(piece, p, 1))
                .filter(p -> ControlsUtility.checkPosition(piece, p, board))
                .filter(p -> !ControlsUtility.checkPiece(piece, p, board) || ControlsUtility.checkEnemy(piece, p, board))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isMovementValid(final Piece piece, final int length, final Position direction, final Chessboard board) {
        final Position p = ControlsUtility.getNewPosition(piece, direction, length);
        if (!ControlsUtility.checkPosition(piece, p, board)) {
            return false;
        }
        final Position previousPosition = ControlsUtility.getNewPosition(piece, direction, length - 1);

        if (ControlsUtility.checkEnemy(piece, previousPosition, board)) {
            return false;
        }
        return !(ControlsUtility.checkPiece(piece, p, board) && !ControlsUtility.checkEnemy(piece, p, board));
    }
}
