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
    Fen side(Side side);

    /**
     * 
     * @return Fen
     */
    Fen castlingQueenside();

    /**
     * 
     * @return Fen
     */
    Fen castlingKingside();

    /**
     * 
     * @param str
     * @return Fen
     */
    Fen enpassant(String str);

    /**
     * 
     * @param halfMove
     * @return Fen
     */
    Fen halfMove(int halfMove);

    /**
     * 
     * @param chessboard
     * @return String
     */
    String build(Chessboard chessboard);

}
