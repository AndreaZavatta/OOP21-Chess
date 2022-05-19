package move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.Chessboard;
import piece.utils.ControlsUtility;
import piece.utils.PieceDirections;
import piece.utils.Position;
import pieces.Piece;
/**
 * This class .
 *
 */
public class BasicMoves {
    /**
     * 
     * @param piece
     * @param board
     * @param directions
     * @return a
     */
    public List<Position> doubleIteration(final PieceDirections directions, final Chessboard board, final Piece piece) {
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

    /**
     * 
     * @param directions
     * @param board
     * @param piece
     * @return a
     */
    public List<Position> singleIteration(final PieceDirections directions, final Chessboard board, final Piece piece) {
        final List<Position> list = new ArrayList<>();
        for (final var pos : directions.directions()) {
            final Position p = ControlsUtility.getNewPosition(piece, pos, 1);
            if (ControlsUtility.checkPosition(piece, p, board)) {
                if (ControlsUtility.checkPiece(piece, p, board)) {
                    if (ControlsUtility.checkEnemy(piece, p, board)) {
                        list.add(p);
                    }
                } else {
                    list.add(p);
                }
            }
        }
        return Collections.unmodifiableList(list);
    }
}
