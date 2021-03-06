package model.move;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.board.Chessboard;
import model.piece.utils.ControlsUtility;
import model.piece.utils.PieceDirections;
import model.piece.utils.Position;
import model.pieces.Piece;
/**
 * Implementation of {@link model.move.PieceMovement} Interface.
 */
public class PieceMovementImpl implements PieceMovement {

    @Override
    public List<Position> multipleMove(final PieceDirections directions, final Chessboard board,
                                       final Piece piece) {
        return directions.getDirections().stream()
                .flatMap(d -> 
                    IntStream.range(1, 8)
                    .takeWhile(i -> isMovementValid(piece, i, d, board))
                    .mapToObj(i -> ControlsUtility.getNewPosition(piece, d, i)))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Position> singleMove(final PieceDirections directions, final Chessboard board,
                                     final Piece piece) {
        return directions.getDirections().stream()
                .map(p -> ControlsUtility.getNewPosition(piece, p, 1))
                .filter(ControlsUtility::checkPositionOnBoard)
                .filter(p -> !ControlsUtility.checkPieceOnPosition(p, board) 
                        || ControlsUtility.checkEnemyOnPosition(piece, p, board))
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
        return !(ControlsUtility.checkPieceOnPosition(p, board)
                && !ControlsUtility.checkEnemyOnPosition(piece, p, board));
    }
}
