package piece.utils;

import java.util.Optional;
import board.Chessboard;
import exceptions.IllegalMoveException;
import pieces.Piece;

public interface Move {

    Optional<Piece> getPiece();

    Optional<Position> getDestination();

    Move setPiece(Piece piece);

    Move setDestination(Position destination);

    boolean isCapture();

    Move setCapture();

    boolean isKingsideCastling();

    Move setKingsideCastling();

    boolean isQueensideCastling();

    Move setQueensideCastling(boolean isQueensideCastling);

    boolean isPromotion();

    Move setPromotion(boolean isPromotion);

    boolean isDrawOffer();

    Move setDrawOffer(boolean isDrawOffer);

    boolean isCheck();

    Move setCheck(boolean isCheck);

    boolean isCheckmate();

    Move setCheckmate(boolean isCheckmate);

    /* this method checks disambiguating moves */
    String build(Chessboard chessboard) throws IllegalMoveException;

}