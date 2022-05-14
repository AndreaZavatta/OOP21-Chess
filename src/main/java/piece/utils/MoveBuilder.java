package piece.utils;

import java.util.Optional;

import board.Chessboard;
import exceptions.IllegalMoveException;
import pieces.Piece;

/**
 * a POJO to represents a move, so it memorizes the start position and the destination position .
 *
 */
public class MoveBuilder implements Move {
    private Optional<Piece> piece = Optional.empty();
    private Optional<Position> destination = Optional.empty();
    private boolean isPromotion = false;
    private boolean isDrawOffer = false;
    private boolean isCheck = false;
    private boolean isCheckmate = false;
    private boolean isCapture = false;
    private boolean isKingsideCastling = false;
    private boolean isQueensideCastling = false;
    @Override
    public Optional<Piece> getPiece() {
        return piece;
    }

    @Override
    public Move setPiece(final Piece piece) {
        this.piece = Optional.ofNullable(piece);
        return this;
    }

    @Override
    public Optional<Position> getDestination() {
        return destination;
    }
    @Override
    public Move setDestination(final Position destination) {
        this.destination = Optional.ofNullable(destination);
        return this;
    }
    @Override
    public boolean isCapture() {
        return isCapture;
    }

    @Override
    public Move setCapture() {
        this.isCapture = true;
        return this;
    }

    @Override
    public boolean isKingsideCastling() {
        return isKingsideCastling;
    }

    @Override
    public Move setKingsideCastling() {
        this.isKingsideCastling = true;
        return this;
    }

    @Override
    public boolean isQueensideCastling() {
        return isQueensideCastling;
    }

    @Override
    public Move setQueensideCastling() {
        this.isQueensideCastling = true;
        return this;
    }

    @Override
    public boolean isPromotion() {
        return isPromotion;
    }

    @Override
    public Move setPromotion() {
        this.isPromotion = true;
        return this;
    }

    @Override
    public boolean isDrawOffer() {
        return isDrawOffer;
    }

    @Override
    public Move setDrawOffer() {
        this.isDrawOffer = true;
        return this;
    }

    @Override
    public boolean isCheck() {
        return isCheck;
    }

    @Override
    public Move setCheck() {
        this.isCheck = true;
        return this;
    }

    @Override
    public boolean isCheckmate() {
        return isCheckmate;
    }

    @Override
    public Move setCheckmate() {
        this.isCheckmate = true;
        return this;
    }

    @Override
    public Move build(final Chessboard chessboard) throws IllegalMoveException {
        if (this.piece.isEmpty() || this.getDestination().isEmpty()) {
            throw new IllegalMoveException();
        }
        return this;
    }
    /**
     * 
     * @return the string representation of the move
     */
    public String toStirng() {
        return "";
    }

}
