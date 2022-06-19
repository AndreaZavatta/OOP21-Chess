package chess.parsers;

import model.board.Chessboard;
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
     * @return Fen to comply with the pattern builder
     */

    Fen activeColor(Side side);

    /**
     * set white king castled kingSide.
     * @return Fen to comply with the pattern builder
     */
    Fen whiteCastlingKingSide();

    /**
     * set white king castled queen side.
     * @return Fen to comply with the pattern builder
     */
    Fen whiteCastlingQueenSide();

    /**
     * set black king castled king side.
     * @return Fen to comply with the pattern builder
     */
    Fen blackCastlingKingSide();

    /**
     * set black king castled queen side.
     * @return Fen to comply with the pattern builder
     */
    Fen blackCastlingQueenSide();

    /**
     * set the position where the pawn can be captured by en passant.
     * @param pos where the pawn can be captured
     * @return Fen to comply with the pattern builder 
     */
    Fen enPassant(String pos);

    /**
     * The halfmove Clock takes care of enforcing the fifty-move rule.
     * This counter is reset after captures or pawn moves, and incremented otherwise
     * 
     * @param halfMove the counter
     * @return Fen to comply with the pattern builder
     */
    Fen halfMoveClock(int halfMove);
    /**
     * 
     * @param fullMoveNumber is the number of semi moves until then
     * @return Fen to comply with the pattern builder
     */
    Fen fullMoveNumber(int fullMoveNumber);

    /**
     * 
     * @param chessboard the chessboard you want to parse
     * @return String representing the chessboard at a given instant
     */
    String build(Chessboard chessboard);





}
