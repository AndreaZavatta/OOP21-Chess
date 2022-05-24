package chess.parsers;

import board.Chessboard;
import piece.utils.Side;
/**
 * an Interface for represent a 
 * position reached during a game of chess.
 * 
 */
public interface Fen {

    /**
     * 
     * @param side
     * @return Fen
     */

    Fen activeColor(Side side);

    /**
     * 
     * @return Fen
     */
    Fen whiteCastledKingside();

    /**
     * 
     * @return Fen
     */
    Fen whiteCastledQueenside();

    /**
     * 
     * @return Fen
     */
    Fen blackCastledKingside();

    /**
     * 
     * @return Fen
     */
    Fen blackCastledQueenside();

    /**
     * 
     * @param str
     * @return Fen
     */
    Fen enpassant(String str);

    /**
     * The Halfmove Clock takes care of enforcing the fifty-move rule.
     * This counter is reset after captures or pawn moves, and incremented otherwise
     * 
     * @param halfMove
     * @return Fen
     */
    Fen halfMoveClock(int halfMove);
    /**
     * 
     * @param fullMoveNumber
     * @return Fen
     */
    Fen fullMoveNumber(int fullMoveNumber);

    /**
     * 
     * @param chessboard
     * @return String
     */
    String build(Chessboard chessboard);





}
