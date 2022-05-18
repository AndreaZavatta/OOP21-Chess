package board;

import static piece.utils.Name.KNIGHT;
import static piece.utils.Name.BISHOP;
import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;
import piece.utils.Side;
import java.util.List;
import java.util.stream.Collectors;
import pieces.Piece;


/**
 * 
 * This Interface models and handles all the possible ways a chess game can end with.
 *
 */
public class EndGameImpl implements EndGame {

    private final boolean gameOver;
    private final ControlCheck controls = new ControlCheckImpl(); 

    /**
     * 
     * @param gameOver
     */
    public EndGameImpl(final boolean gameOver) {
        super();
        this.gameOver = gameOver;
    }


    @Override
    public boolean isGameOver() {
        return gameOver;
    };


    @Override
    public boolean isCheckmate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        if (controlCheck(side, chessboard, controls)) {
            for (final Piece shield : attackedColor) {
                if (!canShield(chessboard, controls, shield)) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean isStalemate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        for (final Piece piece : attackedColor) {
            if (!canShield(chessboard, controls, piece)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean isDrawByInsufficientfMaterial(final Chessboard chessboard) {

        return checkIfRemainingPiecesCauseStalemate(BLACK, chessboard) && checkIfRemainingPiecesCauseStalemate(WHITE, chessboard);
    }

    private boolean checkIfRemainingPiecesCauseStalemate(final Side side, final Chessboard chessboard) {
        final List<Piece> alive = chessboard.getAllPieces().stream()
                .filter(x -> x.getColor() == side)
                .collect(Collectors.toList());
        if (alive.size() > 2) {
            return false;
        } 
        return alive.stream().filter(x -> x.getName() == KNIGHT || x.getName() == BISHOP)
                .findAny()
                .isPresent();
    }
    
    private List<Piece> getAttackedSide(final Side side, final Chessboard chessboard) {
        return chessboard.getAllPieces().stream()
                .filter(x -> x.getColor().equals(side))
                .collect(Collectors.toList());
    }

    // Private method that indicates if the pieces of the attacked side can protect their King.
    private boolean canShield(final Chessboard chessboard, final ControlCheck controls, final Piece shield) {
        return !controls.controlledMoves(chessboard, shield).isEmpty(); 
    }

    // Private method that indicates if the King is in check.
    private boolean controlCheck(final Side side, final Chessboard chessboard, final ControlCheck controls) {
        return controls.isInCheck(chessboard, side);
    }
}
