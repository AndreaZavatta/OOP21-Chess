package piece.utils;

import board.Chessboard;
import exceptions.IllegalMoveException;
import pieces.Piece;

/**
 * 
 *
 */
public interface Move {

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
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCapture();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setKingsideCastling();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setQueensideCastling();


    /**
     * setter for field.
     * @param piece
     * @return this to join the pattern builder
     */
    Move setPromotion(Piece piece);


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setDrawOffer();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCheck();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move setCheckmate();

    /**
     * 
     * @return Move
     */
    Move setRank();

    /**
     * 
     * @return Move
     */
    Move setFile();
    /**
     * @param chessboard
     * @return a string that represent a move
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;



}