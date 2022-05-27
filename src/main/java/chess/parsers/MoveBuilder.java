package chess.parsers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import board.Chessboard;
import board.ControlCheck;
import board.ControlCheckImpl;
import exceptions.IllegalMoveException;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import static piece.utils.Name.PAWN;

/**
 * a MoveBuilder to convert a move to String 
 * represented by algebric notation.
 *
 */
public class MoveBuilder implements Move {
    private final ControlCheck controls = new ControlCheckImpl();
    private Piece piece;
    private Position destination;
    private Name promotion;
    private boolean drawOffer;
    private boolean check;
    private boolean checkmate;
    private boolean capture;
    private boolean kingSideCastling;
    private boolean queenSideCastling;
    private boolean column;
    private boolean row;


    @Override
    public Move piece(final Piece piece) {
        this.piece = piece;
        return this;
    }

    @Override
    public Move destination(final Position destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public Move capture() {
        this.capture = true;
        return this;
    }

    @Override
    public Move kingSideCastling() {
        this.kingSideCastling = true;
        return this;
    }

    @Override
    public Move queenSideCastling() {
        queenSideCastling = true;
        return this;
    }

    @Override
    public Move promotion(final Name piece) {
        this.promotion = piece;
        return this;
    }

    @Override
    public Move drawOffer() {
        this.drawOffer = true;
        return this;
    }

    @Override
    public Move check() {
        this.check = true;
        return this;
    }

    @Override
    public Move checkmate() {
        this.checkmate = true;
        return this;
    }

    @Override
    public Move row() {
        row = true;
        return this;
    }

    @Override
    public Move column() {
        column = true;
        return this;
    }

    @Override
    public Move build(final Chessboard chessboard) throws IllegalMoveException {
        if (!drawOffer && !isCastling() && (piece == null || destination == null)) {
            throw new IllegalMoveException();
        }
        final List<Piece> pieces = verifyDualityOnDestPos(chessboard);
        findPiecesSameRow(pieces).ifPresent(x -> column());
        findPiecesSameColumn(pieces).ifPresent(x -> row());
        return this;
    }
    private boolean isCastling() {
        return kingSideCastling || queenSideCastling;
    }
    /*
     * function that check if there is the same piece in the same row or column that can go in the same destination
     * update the rank and file field
     * */

    private Optional<Piece> findPiecesSameRow(final List<Piece> pieces) {
        return findPiecesFunction(pieces, x -> x.getPosition().getY());
    }
    private Optional<Piece> findPiecesSameColumn(final List<Piece> pieces) {
        return findPiecesFunction(pieces, x -> x.getPosition().getX());
    }
    private Optional<Piece> findPiecesFunction(final List<Piece> pieces, final ToIntFunction<Piece> func) {
        return pieces.stream()
        .filter(x -> Objects.equals(func.applyAsInt(x), func.applyAsInt(piece)))
        .findAny();
    }

    private List<Piece> verifyDualityOnDestPos(final Chessboard chessboard) {
        return getPiecesSameType(chessboard)
                .stream()
                .filter(x -> controls.controlledMoves(chessboard, x).contains(destination))
                .collect(Collectors.toList());
    }

    private List<Piece> getPiecesSameType(final Chessboard chessboard) {
        return Optional.ofNullable(chessboard)
        .map(x -> x.getAllPieces().stream()
        .filter(y -> !y.equals(piece))
        .filter(y -> y.getName().equals(piece.getName()))
        .filter(y -> !y.equals(piece)).collect(Collectors.toList()))
        .orElse(List.of());

    }
    /**
     * 
     * @return the string representation of the move
     */
    @Override
    public String toString() {
        if (drawOffer) {
            return "(=)";
        } else if (kingSideCastling) {
            return "0-0";
        } else if (queenSideCastling) {
            return "0-0-0";
        }
        final StringBuilder str = new StringBuilder();
        addPieceNotation(str);
        addDepartureX(str);
        addDepartureY(str);
        addCapture(str);
        addDestination(str);
        addPromotion(str);
        addCheckConditions(str);
        return str.toString();
    }

    private void addDepartureY(final StringBuilder str) {
        if (row) {
            str.append(piece.getPosition().getY());
        }
    }

    private void addDepartureX(final StringBuilder str) {
        if (column || isPawnCapture()) {
            str.append(piece.getPosition().getCharX());
        }
    }

    private boolean isPawnCapture() {
        return piece.getName().equals(PAWN) && capture;
    }

    private void addPieceNotation(final StringBuilder str) {
        if (!"P".contentEquals(piece.getName().notation())) {
            str.append(nameNotation());
        }

    }

    private void addDestination(final StringBuilder str) {
        str.append(destination);
    }

    private void addPromotion(final StringBuilder str) {
        if (promotion != null) {
            str.append('=').append(promotion.notation());
        }
    }

    private void addCheckConditions(final StringBuilder str) {
        if (check) {
            str.append('+');
        } else if (checkmate) {
            str.append('#');
        }
    }

    private void addCapture(final StringBuilder str) {
        if (capture) {
            str.append('x');
        }
    }


    private String nameNotation() {
        return piece.getName().notation();
    }
}
