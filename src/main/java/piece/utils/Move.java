package piece.utils;

import java.util.Optional;
import board.Chessboard;
import exceptions.IllegalMoveException;
import pieces.Piece;

/**
 * 
 *
 */
public interface Move {
    /**
     * getter for field.
     * @return the moved piece
     */
    Optional<Piece> getPiece();

    /**
     * getter for field.
     * @return the destination position
     */
    Optional<Position> getDestination();

    /**
     * set the moved Piece.
     * @param piece
     * 
     * @return this to join the pattern builder
     */
    Move setPiece(Piece piece);

    /**
     * 
     * @param destination
     * @return this to join the pattern builder
     */
    Move setDestination(Position destination);

    /**
     * getter for field.
     * @return isCapture field
     */
    boolean isCapture();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCapture();

    /**
     * getter for field.
     * @return if the type of move is a kingside castling
     */
    boolean isKingsideCastling();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setKingsideCastling();

    /**
     * getter for field.
     * @return if the type of move is a queenside castling
     */
    boolean isQueensideCastling();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setQueensideCastling();

    /**
     * getter for field.
     * @return if the type of move is a promotion
     */
    boolean isPromotion();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setPromotion();

    /**
     * getter for field.
     * @return if the type of move is a drawOffer
     */
    boolean isDrawOffer();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setDrawOffer();

    /**
     * getter for field.
     * @return if the type of move is a check move 
     */
    boolean isCheck();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCheck();

    /**
     * getter for field.
     * @return if the type of move is a checkmate move
     */
    boolean isCheckmate();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCheckmate();

    /**
     * @param chessboard
     * @return a string that represent a move
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;

}