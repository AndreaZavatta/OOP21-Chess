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
    Fen setSide(Side side);

    /**
     * 
     * @return Fen
     */
    Fen setCastlingQueenside();

    /**
     * 
     * @return Fen
     */
    Fen setCastlingKingside();

    /**
     * 
     * @param str
     * @return Fen
     */
    Fen setEnpassant(String str);

    /**
     * 
     * @param halfMove
     * @return Fen
     */
    Fen setHalfMove(int halfMove);

    /**
     * 
     * @param chessboard
     * @return String
     */
    String build(Chessboard chessboard);

}
