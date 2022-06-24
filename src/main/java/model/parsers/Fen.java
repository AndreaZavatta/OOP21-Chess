package model.parsers;

import model.board.Chessboard;
import model.piece.utils.Side;
/**
 * An Interface for represent a
 * position reached during a game of chess.
 * 
 */
public interface Fen {

    /**
     * Set the active color.
     * @param side the color that must move to this position on the chessboard.
     * @return Fen to comply with the pattern builder
     */

    Fen activeColor(Side side);

    /**
     * Set white king castled kingSide.
     * @return Fen to comply with the pattern builder
     */
    Fen whiteCastlingKingSide();

    /**
     * Set white king castled queen side.
     * @return Fen to comply with the pattern builder
     */
    Fen whiteCastlingQueenSide();

    /**
     * Set black king castled king side.
     * @return Fen to comply with the pattern builder
     */
    Fen blackCastlingKingSide();

    /**
     * Set black king castled queen side.
     * @return Fen to comply with the pattern builder
     */
    Fen blackCastlingQueenSide();

    /**
     * Set the position where the pawn can be captured by en passant.
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
     * Set the numero of semi moves.
     * @param fullMoveNumber is the number of semi moves until then
     * @return Fen to comply with the pattern builder
     */
    Fen fullMoveNumber(int fullMoveNumber);

    /**
     * This method create the String from Chessboard.
     * @param chessboard the chessboard you want to parse
     * @return String representing the chessboard at a given instant
     */
    String build(Chessboard chessboard);





}
