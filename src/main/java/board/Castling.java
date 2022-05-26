package board;

import java.util.Optional;

import piece.utils.Position;
import piece.utils.Side;

/**
 * This interface handles all Castling opportunities.
 */
public interface Castling {

    /**
     * 
     * @param chessboard
     * @param side
     * @param xPos
     * @return true when it is possible to Castle on the given side of the king
     */
    boolean canCastle(Chessboard chessboard, Side side, int xPos);

    Optional<Position> canCastle1(Chessboard chessboard, Side side, int xPos);

}
