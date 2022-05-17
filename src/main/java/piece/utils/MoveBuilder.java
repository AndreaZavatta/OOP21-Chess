package piece.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ControlCheck;
import board.ControlCheckImpl;
import exceptions.IllegalMoveException;
import pieces.Piece;

/**
 * a POJO to represents a move, so it memorizes the start position and the destination position .
 *
 */
public class MoveBuilder implements Move {
    private final ControlCheck controls = new ControlCheckImpl();
    private Optional<Piece> piece;
    private Optional<Position> destination;
    private Optional<Piece> promotion;
    private boolean drawOffer;
    private boolean check;
    private boolean checkmate;
    private boolean capture;
    private boolean kingsideCastling;
    private boolean queenSideCastling;
    private boolean file;
    private boolean rank;


    @Override
    public Move setPiece(final Piece piece) {
        this.piece = Optional.ofNullable(piece);
        return this;
    }

    @Override
    public Move setDestination(final Position destination) {
        this.destination = Optional.ofNullable(destination);
        return this;
    }

    @Override
    public Move setCapture() {
        this.capture = true;
        return this;
    }

    @Override
    public Move setKingsideCastling() {
        this.kingsideCastling = true;
        return this;
    }

    @Override
    public Move setQueensideCastling() {
        queenSideCastling = true;
        return this;
    }

    @Override
    public Move setPromotion(final Piece piece) {
        this.promotion = Optional.ofNullable(piece);
        return this;
    }

    @Override
    public Move setDrawOffer() {
        this.drawOffer = true;
        return this;
    }

    @Override
    public Move setCheck() {
        this.check = true;
        return this;
    }

    @Override
    public Move setCheckmate() {
        this.checkmate = true;
        return this;
    }

    @Override
    public Move setRank() {
        rank = true;
        return this;
    }

    @Override
    public Move setFile() {
        file = true;
        return this;
    }

    @Override
    public Move build(final Chessboard chessboard) throws IllegalMoveException {
        List<Piece> pieces = verifyDualityOnDestPos(chessboard);
        findPiecesSameRank(pieces).ifPresent(x -> setRank());
        findPiecesSameFile(pieces).ifPresent(x -> setFile());
        if (rank && file) {
            setFile();
        }
        return this;
    }
    /*
     * function that check if there is the same piece in the same row or column that can go in the same destination
     * update the rank and file field
     * */

    private Optional<Piece> findPiecesSameRank(final List<Piece> pieces) {
        return pieces.stream()
        .filter(x -> (x.getPosition().getX() == (piece.get().getPosition().getX())))
        .findAny();
    }
    private Optional<Piece> findPiecesSameFile(final List<Piece> pieces) {
        return pieces.stream()
        .filter(x -> (x.getPosition().getY() == (piece.get().getPosition().getY())))
        .findAny();
    }

    private List<Piece> verifyDualityOnDestPos(final Chessboard chessboard) {
        return getPiecesSameType(chessboard)
                .stream()
                .filter(x -> controls.controlledMoves(chessboard, x).contains(destination.get()))
                .collect(Collectors.toList());
    }

    private List<Piece> getPiecesSameType(final Chessboard chessboard) {
        return chessboard.getAllPieces().stream()
        .filter(x -> x.getName().equals(piece.get().getName()))
        .filter(x -> !x.equals(piece.get())).collect(Collectors.toList());
    }
    /**
     * 
     * @return the string representation of the move
     */
    public String toStirng() {
        StringBuilder str = new StringBuilder();
        if (drawOffer) {
            return "(=)";
        } else if (kingsideCastling) {
            return "0-0";
        } else if (queenSideCastling) {
            return "0-0-0";
        }
        str.append(piece.get().getName().notation());
        return str.toString();
    }

}
