package chess.parsers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import model.board.Chessboard;
import model.board.ControlCheck;
import model.board.ControlCheckImpl;
import exceptions.IllegalMoveException;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;
import static model.piece.utils.Name.PAWN;

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
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Piece> getPiecesSameType(final Chessboard chessboard) {
        return Optional.ofNullable(chessboard)
        .map(x -> x.getAllPieces().stream()
        .filter(y -> !y.equals(piece))
        .filter(y -> y.getName().equals(piece.getName()))
        .filter(y -> !y.equals(piece)).collect(Collectors.toUnmodifiableList()))
        .orElse(List.of());

    }
    /**
     * 
     * @return the string representation of the move
     */
    @Override
    public String toString() {
        String str;
        if (drawOffer) {
            str = "(=)";
        } else if (kingSideCastling) {
            str = "0-0";
        } else if (queenSideCastling) {
            str = "0-0-0";
        } else {
            str = getMove();
        }
        return str;

    }

    private String getMove() {
        return getPieceNotation()
                + getDepartureX()
                + getDepartureY()
                + getCapture()
                + destination
                + getPromotion()
                + getCheckConditions();
    }

    private String getDepartureY() {
        return row ? Integer.toString(piece.getPosition().getY()) : "";
    }

    private String getDepartureX() {
        return (column || isPawnCapture()) ? Character.toString(piece.getPosition().getCharX()) : "";
    }

    private boolean isPawnCapture() {
        return piece.getName().equals(PAWN) && capture;
    }

    private String getPieceNotation() {
        return (!"P".contentEquals(piece.getName().getChessNotation())) ? nameNotation() : "";
    }

    private String getPromotion() {
        return (promotion != null) ? "=" + promotion.getChessNotation() : "";
    }

    private String getCheckConditions() {
        if (check) {
            return "+";
        } else if (checkmate) {
            return "#";
        } else {
            return "";
        }
    }

    private String getCapture() {
        return capture ? "x" : "";
    }


    private String nameNotation() {
        return piece.getName().getChessNotation();
    }
}
