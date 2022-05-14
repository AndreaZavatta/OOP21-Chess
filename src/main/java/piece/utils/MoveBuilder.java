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
    public Move setPiece(Piece piece) {
        this.piece = Optional.ofNullable(piece);
        return this;
    }

    @Override
    public Optional<Position> getDestination() {
        return destination;
    }
    
    @Override
    public Move setDestination(Position destination) {
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
    public Move setQueensideCastling(boolean isQueensideCastling) {
        this.isQueensideCastling = isQueensideCastling;
        return this;
    }

    @Override
    public boolean isPromotion() {
        return isPromotion;
    }

    @Override
    public Move setPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
        return this;
    }

    @Override
    public boolean isDrawOffer() {
        return isDrawOffer;
    }

    @Override
    public Move setDrawOffer(boolean isDrawOffer) {
        this.isDrawOffer = isDrawOffer;
        return this;
    }

    @Override
    public boolean isCheck() {
        return isCheck;
    }

    @Override
    public Move setCheck(boolean isCheck) {
        this.isCheck = isCheck;
        return this;
    }

    @Override
    public boolean isCheckmate() {
        return isCheckmate;
    }

    @Override
    public Move setCheckmate(boolean isCheckmate) {
        this.isCheckmate = isCheckmate;
        return this;
    }

    @Override
    public String build(Chessboard chessboard) throws IllegalMoveException{
        if(this.piece.isEmpty() || this.getDestination().isEmpty()) {
            throw new IllegalMoveException();
        }
        return "";
    }

}
