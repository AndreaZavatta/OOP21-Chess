package piece.utils;

import java.util.Optional;

import board.Chessboard;
import exceptions.IllegalMoveException;
import pieces.Piece;

/**
 * a POJO to represents a move, so it memorizes the start position and the destination position .
 *
 */
public class MoveBuilder {
    private Optional<Piece> piece = Optional.empty();
    private Optional<Position> destination = Optional.empty();
    private boolean isPromotion = false;
    private boolean isDrawOffer = false;
    private boolean isCheck = false;
    private boolean isCheckmate = false;
    private boolean isCapture = false;
    private boolean isKingsideCastling = false;
    private boolean isQueensideCastling = false;
    
    public Optional<Piece> getPiece() {
        return piece;
    }

    public MoveBuilder setPiece(Optional<Piece> piece) {
        this.piece = piece;
        return this;
    }

    public Optional<Position> getDestination() {
        return destination;
    }
    
    public MoveBuilder setDestination(Optional<Position> destination) {
        this.destination = destination;
        return this;
    }
    
    public boolean isCapture() {
        return isCapture;
    }

    public MoveBuilder setCapture(boolean isCapture) {
        this.isCapture = isCapture;
        return this;
    }

    public boolean isKingsideCastling() {
        return isKingsideCastling;
    }

    public MoveBuilder setKingsideCastling(boolean isKingsideCastling) {
        this.isKingsideCastling = isKingsideCastling;
        return this;
    }

    public boolean isQueensideCastling() {
        return isQueensideCastling;
    }

    public MoveBuilder setQueensideCastling(boolean isQueensideCastling) {
        this.isQueensideCastling = isQueensideCastling;
        return this;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public MoveBuilder setPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
        return this;
    }

    public boolean isDrawOffer() {
        return isDrawOffer;
    }

    public MoveBuilder setDrawOffer(boolean isDrawOffer) {
        this.isDrawOffer = isDrawOffer;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public MoveBuilder setCheck(boolean isCheck) {
        this.isCheck = isCheck;
        return this;
    }

    public boolean isCheckmate() {
        return isCheckmate;
    }

    public MoveBuilder setCheckmate(boolean isCheckmate) {
        this.isCheckmate = isCheckmate;
        return this;
    }

    

    public MoveBuilder build(Chessboard chessboard) throws IllegalMoveException{
        if(this.piece.isEmpty()) {
            throw new IllegalMoveException();
        }
        return this;
    }
}
