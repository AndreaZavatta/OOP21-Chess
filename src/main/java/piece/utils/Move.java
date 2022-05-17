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
    Move piece(Piece piece);

    /**
     * 
     * @param destination
     * @return this to join the pattern builder
     */
    Move destination(Position destination);

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move capture();

    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move kingSideCastling();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move queenSideCastling();


    /**
     * setter for field.
     * @param piece
     * @return this to join the pattern builder
     */
    Move promotion(Piece piece);


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move drawOffer();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move check();


    /**
     * setter for field.
     * @return this to join the pattern builder
     */
    Move checkMate();

    /**
     * 
     * @return Move
     */
    Move rank();

    /**
     * 
     * @return Move
     */
    Move file();
    /**
     * @param chessboard
     * @return a string that represent a move
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;
}
