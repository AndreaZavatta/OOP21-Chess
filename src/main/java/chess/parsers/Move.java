package chess.parsers;
import model.board.Chessboard;
import exceptions.IllegalMoveException;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;

/**
 * this interface is used to convert a move into a string.
 *
 */
public interface Move {

    /**
     * set the moved Piece.
     * @param piece the piece moved
     * 
     * @return Move to join the pattern builder
     */
    Move piece(Piece piece);

    /**
     * 
     * @param destination the destination of the piece moved
     * @return Move to join the pattern builder
     */
    Move destination(Position destination);

    /**
     * set this is a capture move.
     * @return Move to join the pattern builder
     */
    Move capture();

    /**
     * set this is a king side castling move.
     * @return Move to join the pattern builder
     */
    Move kingSideCastling();


    /**
     * set this is a queen side castling move.
     * @return Move to join the pattern builder
     */
    Move queenSideCastling();


    /**
     * set this is a promotion move.
     * @param piece set the name of the piece moved
     * @return Move to join the pattern builder
     */
    Move promotion(Name piece);


    /**
     * set this is a draw offer.
     * @return Move to join the pattern builder
     */
    Move drawOffer();


    /**
     * set this move puts the king in check.
     * @return Move to join the pattern builder
     */
    Move check();


    /**
     * that this move puts the king in checkmate.
     * @return Move to join the pattern builder
     */
    Move checkmate();

    /**
     * set there is a piece of the same type and color and in the same row of departure
     * that can go in the same destination position.
     * @return Move to join the pattern builder
     */
    Move row();

    /**
     ** set there is a piece of the same type and color and in the same row of departure
     ** that can go in the same destination position.
     * @return Move to join the pattern builder
     */
    Move column();
    /**
     * @param chessboard the chessboard on which the move is made
     * @return a string that represent a move
     * @throws IllegalMoveException if the move isn't valid
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;
}
