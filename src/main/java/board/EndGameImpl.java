package board;

import static model.piece.utils.Name.KNIGHT;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
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

    private static final long serialVersionUID = 8138985175091153307L;
    private final transient ControlCheck controls = new ControlCheckImpl();

    @Override
    public boolean isCheckmate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        if (controlCheck(side, chessboard, controls)) {
            for (final Piece shield : attackedColor) {
                if (!cannotShield(chessboard, controls, shield)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isStalemate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        for (final Piece piece : attackedColor) {
            if (!cannotShield(chessboard, controls, piece)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDrawByInsufficientMaterial(final Chessboard chessboard) {
        return checkIfRemainingPiecesCauseStalemate(BLACK, chessboard) && checkIfRemainingPiecesCauseStalemate(WHITE, chessboard);
    }

    private boolean checkIfRemainingPiecesCauseStalemate(final Side side, final Chessboard chessboard) {
        final List<Piece> alive = chessboard.getAllPieces().stream()
                .filter(x -> x.getSide() == side)
                .collect(Collectors.toList());
        if (alive.size() > 2) {
            return false;
        } 
        return alive.stream().anyMatch(x -> x.getName() == KNIGHT || x.getName() == BISHOP);
    }

/*    @Override
    public boolean isDrawByRepetition(final Chessboard chessboard) {
        return true;
    }*/

    @Override
    public boolean isDraw(final Side side, final Chessboard chessboard) {
        return isDrawByInsufficientMaterial(chessboard) /*|| isDrawByRepetition(chessboard)*/ || isStalemate(side, chessboard);
    }

    private List<Piece> getAttackedSide(final Side side, final Chessboard chessboard) {
        return chessboard.getAllPieces().stream()
                .filter(x -> x.getSide().equals(side))
                .collect(Collectors.toList());
    }

    // Private method that indicates if the pieces of the attacked side can protect their King.
    private boolean cannotShield(final Chessboard chessboard, final ControlCheck controls, final Piece shield) {
        return controls.controlledMoves(chessboard, shield).isEmpty();
    }

    // Private method that indicates if the King is in check.
    private boolean controlCheck(final Side side, final Chessboard chessboard, final ControlCheck controls) {
        return controls.isInCheckWithoutKing(chessboard, side);
    }
}
