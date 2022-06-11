package chess.parsers;

import board.Chessboard;
import model.piece.utils.Side;
/**
 * an Interface for represent a 
 * position reached during a game of chess.
 * 
 */
public interface Fen {

    /**
     * 
     * @param side the color that must move to this position on the chessboard.
     * @return Fen
     */

    Fen activeColor(Side side);

    /**
     * set white king castled kingSide
     * @return Fen
     */
    Fen whiteCastlingKingSide();

    /**
     * set white king castled queen side
     * @return Fen
     */
    Fen whiteCastlingQueenSide();

    /**
     * set black king castled king side
     * @return Fen
     */
    Fen blackCastlingKingSide();

    /**
     * set black king castled queen side
     * @return Fen
     */
    Fen blackCastlingQueenSide();

    /**
     * set
     * @param pos set the position where the pawn can be captured by en passant
     * @return Fen
     */
    Fen enPassant(String pos);

    /**
     * The halfmove Clock takes care of enforcing the fifty-move rule.
     * This counter is reset after captures or pawn moves, and incremented otherwise
     * 
     * @param halfMove the counter
     * @return Fen
     */
    Fen halfMoveClock(int halfMove);
    /**
     * 
     * @param fullMoveNumber is the number of semi moves until then
     * @return Fen
     */
    Fen fullMoveNumber(int fullMoveNumber);

    /**
     * 
     * @param chessboard the chessboard you want to parse
     * @return String
     */
    String build(Chessboard chessboard);





}
