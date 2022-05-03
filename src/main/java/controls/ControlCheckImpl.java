package controls;

import piece.utils.Color;
import piece.utils.Name;
import java.util.List;
import piece.utils.Position;
import board.Chessboard;
import pieces.Piece;

/**
 * 
 *
 */

public class ControlCheckImpl implements ControlCheck {

    @Override
    public List<Position> removeMoveInCheck(final Chessboard chessboard, final Piece piece, final List<Position> avaliableMoves) {
        // TODO Auto-generated method stub
        return List.of();
    }

    @Override
    public boolean isInCheck(final Chessboard chessboard, final Color color) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(color))
                .filter(x -> canEatKing(chessboard, x))
                .findFirst().isPresent();
    }
    private boolean canEatKing(final Chessboard chessboard, final Piece piece) {
        return piece.getAllPossiblePositions(chessboard).contains(getEnemyKingPosition(chessboard, piece));
    }
    private Position getEnemyKingPosition(final Chessboard chessboard, final Piece piece) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(piece.getColor()))
                .filter(x -> x.getName().equals(Name.KING))
                .findFirst()
                .get().getPosition();
    }



}
