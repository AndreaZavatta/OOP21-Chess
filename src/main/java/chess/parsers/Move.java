package chess.parsers;
import model.board.Chessboard;
import exceptions.IllegalMoveException;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;

/**
 * This interface is used to convert a move into a string.
 *
 */
public interface Move {

    /**
     * Set the moved Piece.
     * @param piece the piece moved
     * 
     * @return Move to join the pattern builder
     */
    Move piece(Piece piece);

    /**
     * Set the destination of the piece moved.
     * @param destination the destination of the piece moved
     * @return Move to join the pattern builder
     */
    Move destination(Position destination);

    /**
     * Set this is a capture move.
     * @return Move to join the pattern builder
     */
    Move capture();

    /**
     * Set this is a king side castling move.
     * @return Move to join the pattern builder
     */
    Move kingSideCastling();


    /**
     * Set this is a queen side castling move.
     * @return Move to join the pattern builder
     */
    Move queenSideCastling();


    /**
     * Set this is a promotion move.
     * @param piece set the name of the piece moved
     * @return Move to join the pattern builder
     */
    Move promotion(Name piece);


    /**
     * Set this is a draw offer.
     * @return Move to join the pattern builder
     */
    Move drawOffer();


    /**
     * Set this move puts the king in check.
     * @return Move to join the pattern builder
     */
    Move check();


    /**
     * Set this move puts the king in checkmate.
     * @return Move to join the pattern builder
     */
    Move checkmate();

    /**
     * Set there is a piece of the same type and color and in the same row of departure
     * that can go in the same destination position.
     * @return Move to join the pattern builder
     */
    Move row();

    /**
     ** Set there is a piece of the same type and color and in the same row of departure
     ** that can go in the same destination position.
     * @return Move to join the pattern builder
     */
    Move column();
    /**
     * This method is used for building Move from Chessboard, based on fields.
     * @param chessboard the chessboard on which the move is made
     * @return a string that represent a move
     * @throws IllegalMoveException if the move isn't valid
     */
    Move build(Chessboard chessboard) throws IllegalMoveException;
}
