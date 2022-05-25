package chess.parsers;
import board.Chessboard;
import exceptions.IllegalMoveException;
import piece.utils.Name;
import piece.utils.Position;
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
     * @return Move to join the pattern builder
     */
    Move piece(Piece piece);

    /**
     * 
     * @param destination
     * @return Move to join the pattern builder
     */
    Move destination(Position destination);

    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move capture();

    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move kingSideCastling();


    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move queenSideCastling();


    /**
     * setter for field.
     * @param piece
     * @return Move to join the pattern builder
     */
    Move promotion(Name piece);


    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move drawOffer();


    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move check();


    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move checkmate();

    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move row();

    /**
     * setter for field.
     * @return Move to join the pattern builder
     */
    Move column();
    /**
     * @param chessboard
     * @return a string that represent a move
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;
}
