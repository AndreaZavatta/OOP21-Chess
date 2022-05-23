package move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
//        directions.directions().stream()
//            .map
//            .map(p -> ControlsUtility.getNewPosition(piece, p, 1)); takewhile
        final List<Position> list = new ArrayList<>();
        for (final var pos : directions.directions()) {
            for (int i = 1; i < 8; i++) {
                final Position p = ControlsUtility.getNewPosition(piece, pos, i);
                if (ControlsUtility.checkPiece(piece, p, board)) {
                    if (ControlsUtility.checkEnemy(piece, p, board)) {
                        list.add(p);
                    } 
                    break;
                } else if (ControlsUtility.checkPosition(piece, p, board)) {
                    list.add(p);
                } else {
                    break;
                }
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<Position> singleIteration(final PieceDirections directions, final Chessboard board, final Piece piece) {
         return directions.directions().stream()
            .map(p -> ControlsUtility.getNewPosition(piece, p, 1))
            .filter(p -> ControlsUtility.checkPosition(piece, p, board))
            .filter(p -> !ControlsUtility.checkPiece(piece, p, board) || ControlsUtility.checkEnemy(piece, p, board))
            .collect(Collectors.toUnmodifiableList());
    }
}
