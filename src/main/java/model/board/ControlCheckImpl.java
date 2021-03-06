package model.board;

import static model.piece.utils.Name.KING;

import model.piece.utils.Side;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.piece.utils.Position;
import model.exceptions.KingNotFoundException;
import model.pieces.Piece;

/**
 * 
 * Simple implementation of ControlCheck.
 */
public class ControlCheckImpl implements ControlCheck {
  private final ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    @Override
    public List<Position> controlledMoves(final Chessboard chessboard, final Piece piece) {
        final List<Position> availableMoves = new ArrayList<>(piece.getAllPossiblePositions(chessboard));
        availableMoves.removeIf(x -> isMoveInCheck(chessboard, piece, x));
        return availableMoves;
    }

    private boolean isMoveInCheck(final Chessboard chessboard, final Piece piece, final Position pos) {
        return isInCheck(simulateMove(chessboard, piece, pos), piece.getSide());
    }
    private Chessboard simulateMove(final Chessboard chessboard, final Piece piece, final Position destPos) {
        final ChessboardImpl chessboardCopy = copyChessboard(chessboard);
        moveIfPresent(piece, destPos, chessboardCopy);
        return chessboardCopy;
    }

    private void moveIfPresent(final Piece piece, final Position destPos, final ChessboardImpl chessboardCopy) {
        chessboardCopy.getAllPieces().stream().filter(x -> x.getPosition().equals(piece.getPosition()))
        .findFirst()
        .ifPresent(x -> chessboardCopy.moveWithoutChecks(x, destPos));
    }

    private ChessboardImpl copyChessboard(final Chessboard chessboard) {
        return (ChessboardImpl) chessboardFact.createTestCB(chessboard.getAllPieces());
    }

    private boolean isInCheckSupport(final Chessboard chessboard, final Side color, final Predicate<Piece> pred) {
        return chessboard.getAllPieces().stream()
                .filter(isOppositeColor(color))
                .filter(pred)
                .anyMatch(x -> canEatKing(chessboard, x));
    }

    private Predicate<Piece> isOppositeColor(final Side color) {
        return x -> !x.getSide().equals(color);
    }

    @Override
    public boolean isInCheck(final Chessboard chessboard, final Side color) {
        return isInCheckSupport(chessboard, color, x -> true);
    }
    @Override
    public boolean isInCheckWithoutKing(final Chessboard chessboard, final Side color) {
        return isInCheckSupport(chessboard, color, x -> !isPieceKing(x));
    }

    private boolean canEatKing(final Chessboard chessboard, final Piece piece) {
        return piece.getAllPossiblePositions(chessboard)
                .contains(getEnemyKingPosition(chessboard, piece));
    }
    private Position getEnemyKingPosition(final Chessboard chessboard, final Piece piece) {
        return chessboard.getAllPieces().stream()
                .filter(isPieceOppositeColor(piece))
                .filter(this::isPieceKing)
                .findFirst()
                .map(Piece::getPosition)
                .orElseThrow(KingNotFoundException::new);
    }

    private Predicate<Piece> isPieceOppositeColor(final Piece piece) {
        return x -> !x.getSide().equals(piece.getSide());
    }

    private boolean isPieceKing(final Piece piece) {
        return piece.getName().equals(KING);
    }

}
