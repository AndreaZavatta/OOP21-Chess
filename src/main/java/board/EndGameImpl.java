package board;

import static model.piece.utils.Name.KNIGHT;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;

import model.piece.utils.Name;
import model.piece.utils.Side;
import java.util.List;
import java.util.stream.Collectors;
import model.pieces.Piece;

/**
 * 
 * Implementation of EndGame Interface's methods.
 *
 */
public class EndGameImpl implements EndGame {
    private final transient ControlCheck controls = new ControlCheckImpl();

    @Override
    public boolean isCheckmate(final Chessboard chessboard, final Side side) {
        final List<Piece> attackedColor = getAttackedSide(chessboard, side);

        if (controlCheck(chessboard, side, controls)) {
            for (final Piece shield : attackedColor) {
                if (canShield(chessboard, controls, shield)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isStalemate(final Chessboard chessboard, final Side side) {
        final List<Piece> attackedColor = getAttackedSide(chessboard, side);

        for (final Piece piece : attackedColor) {
            if (canShield(chessboard, controls, piece)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDrawByInsufficientMaterial(final Chessboard chessboard) {
        return checkIfRemainingPiecesCauseStalemate(chessboard, BLACK) && checkIfRemainingPiecesCauseStalemate(chessboard, WHITE);
    }

    private boolean checkIfRemainingPiecesCauseStalemate(final Chessboard chessboard, final Side side) {
        final List<Piece> alive = chessboard.getAllPieces().stream()
                .filter(x -> x.getSide() == side)
                .collect(Collectors.toList());
        if (alive.size() > 2) {
            return false;
        }
        if (alive.size() == 1 && alive.stream().map(Piece::getName).collect(Collectors.toList()).contains(Name.KING)) {
            return true;
        }
        return alive.stream().anyMatch(x -> x.getName() == KNIGHT || x.getName() == BISHOP);
    }

    /*@Override
    public boolean isDrawByRepetition(final Chessboard chessboard) {
        return true;
    }*/

    @Override
    public boolean isDraw(final Chessboard chessboard, final Side side) {
        return isDrawByInsufficientMaterial(chessboard) /*|| isDrawByRepetition(chessboard)*/ || isStalemate(chessboard, side);
    }

    private List<Piece> getAttackedSide(final Chessboard chessboard, final Side side) {
        return chessboard.getAllPieces().stream()
                .filter(x -> x.getSide().equals(side))
                .collect(Collectors.toList());
    }

    // Private method that indicates if the pieces of the attacked side can protect their King.
    private boolean canShield(final Chessboard chessboard, final ControlCheck controls, final Piece shield) {
        return !controls.controlledMoves(chessboard, shield).isEmpty();
    }
    // Private method that indicates if the King is in check.
    private boolean controlCheck(final Chessboard chessboard, final Side side, final ControlCheck controls) {
        return controls.isInCheckWithoutKing(chessboard, side);
    }
}
