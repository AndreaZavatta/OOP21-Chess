package board;

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

    /**
     * 
     * @return
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    };

    /**
     * 
     * @param side
     * @param chessboard
     * @return
     */
    @Override
    public boolean isCheckmate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        if (controlCheck(side, chessboard, controls)) {
            for (final Piece shield : attackedColor) {
                if (!checkShieldPosition(chessboard, controls, shield)) {
                   return gameOver;
                } 
            }
        }
        return !gameOver;
    }

    /**
     * 
     * @param side
     * @param chessboard
     * @return
     */
    @Override
    public boolean isStalemate(final Side side, final Chessboard chessboard) {

        final List<Piece> attackedColor = getAttackedSide(side, chessboard);

        for (final Piece piece : attackedColor) {
            if (!checkShieldPosition(chessboard, controls, piece)) {
                return gameOver;
            }
        }
        return !gameOver;
    }


    private List<Piece> getAttackedSide(final Side side, final Chessboard chessboard) {
        return chessboard.getAllPieces().stream()
                .filter(x -> x.getColor().equals(side))
                .collect(Collectors.toList());
    }

    private boolean checkShieldPosition(final Chessboard chessboard, final ControlCheck controls, final Piece shield) {
        return controls.controlledMoves(chessboard, shield).isEmpty(); 
    }

    private boolean controlCheck(final Side side, final Chessboard chessboard, final ControlCheck controls) {
        return controls.isInCheck(chessboard, side);
    }

}
