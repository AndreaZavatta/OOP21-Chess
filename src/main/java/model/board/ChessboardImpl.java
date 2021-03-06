package model.board;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.pieces.Piece;
import model.promotion.Promotion;
import model.promotion.PromotionImpl;

    /**
     * 
     * A normal chessboard.
     *
     */

    class ChessboardImpl implements Chessboard {
        @JsonProperty("allPieces")
    private final List<Piece> piecesList;
    private final int xBorder;
    private final int yBorder;
    private final Promotion promotion;


    ChessboardImpl(final List<Piece> piecesList, final int yBorder, final int xBorder) {
        this.piecesList = piecesList;
        this.xBorder = xBorder;
        this.yBorder = yBorder;
        this.promotion = new PromotionImpl();
    }


    @Override
    public List<Piece> getAllPieces() {
        return Collections.unmodifiableList(this.piecesList);
    }

    @Override
    public void move(final Position actualPos, final Position finalPos) {
        final Piece attacker = this.getPieceOnPosition(actualPos).get();
        this.moveWithoutChecks(attacker, finalPos);
    }

    public int getxBorder() {
        return this.xBorder;
    }

    public int getyBorder() {
        return this.yBorder;
    }

    @Override
    public String toString() {
        return "Chessboard: " + this.piecesList.stream().map(Piece::toString)
                                            .collect(Collectors.joining(" | "));
    }

    @Override
    public Optional<Piece> getPieceOnPosition(final Position selectedPos) {
        return piecesList.stream()
                .filter(t -> t.getPosition().equals(selectedPos))
                .findFirst();
    }

    @Override
    public List<Position> getAllPosition(final Piece attacker) {
        final ControlCheck movesController = new ControlCheckImpl();
        return movesController.controlledMoves(this, attacker);
    }

    @Override
    public Piece promotion(final Name namePiece) {
        final Piece oldPiece = promotion.checkForPromotion(piecesList).get();
        final Piece newPiece = promotion.changePiece(namePiece, oldPiece);
        this.piecesList.remove(oldPiece);
        this.piecesList.add(newPiece);
        return newPiece;
    }

    private boolean canKill(final Position targetPos) {
        return this.getPieceOnPosition(targetPos).isPresent();
    }

    protected void moveWithoutChecks(final Piece piece, final Position targetPos) {
        if (this.canKill(targetPos)) {
           this.piecesList.remove(this.getPieceOnPosition(targetPos).get()); 
        }
        if (isCastling(piece, targetPos)) {
            if (targetPos.getX() - piece.getPosition().getX() < 0) {
                final Piece rookLeft = getPieceOnPosition(Position.createNumericPosition(Numbers.ZERO, piece.getPosition().getY())).get();
                rookLeft.setPosition(Position.createNumericPosition(targetPos.getX() + 1, targetPos.getY()));
            } else if (targetPos.getX() - piece.getPosition().getX() > 0) {
                final Piece rookRight = getPieceOnPosition(Position.createNumericPosition(Numbers.SEVEN, piece.getPosition().getY())).get();
                rookRight.setPosition(Position.createNumericPosition(targetPos.getX() - 1, targetPos.getY()));
            }
        }
        piece.setPosition(targetPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piecesList);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChessboardImpl other = (ChessboardImpl) obj;
        return piecesList.containsAll(other.piecesList) && other.piecesList.containsAll(piecesList);
    }

    @Override
    public boolean isCastling(final Piece piece, final Position targetPos) {
        return piece.getName().equals(Name.KING) && (targetPos.getX() == piece.getPosition().getX() + 2 || targetPos.getX() == piece.getPosition().getX() - 2);
    }
}
