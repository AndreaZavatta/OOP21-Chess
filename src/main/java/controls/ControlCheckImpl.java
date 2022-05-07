package controls;

import static piece.utils.Name.KING;
import piece.utils.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import piece.utils.Position;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import pieces.Piece;

/**
 * 
 *
 */

public class ControlCheckImpl implements ControlCheck {
  private final ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    @Override
    public List<Position> removeMovesInCheck(final Chessboard chessboard, final Piece piece) {
        final List<Position> avaliableMoves = new ArrayList<>(piece.getAllPossiblePositions(chessboard));
        avaliableMoves.removeIf(x -> this.isMoveInCheck(chessboard, piece, x));
        return avaliableMoves;
    }

    private boolean isMoveInCheck(final Chessboard chessboard, final Piece piece, final Position pos) {
        return isInCheck(simulateMove(chessboard, piece, pos), piece.getColor());
    }
    private Chessboard simulateMove(final Chessboard chessboard, final Piece piece, final Position pos) {
        final Chessboard chessboardCopy = copyChessboard(chessboard);
        setPosition(piece, pos, chessboardCopy);
        return chessboardCopy;
    }
    private Chessboard copyChessboard(final Chessboard chessboard) {
        return chessboardFact.createTestCB(chessboard.getAllPieces());
    }
    private void setPosition(final Piece piece, final Position pos, final Chessboard chessboardCopy) {
        chessboardCopy.getAllPieces().stream()
        .filter(x -> x.getPosition().equals(piece.getPosition()))
        .findFirst()
        .ifPresent(x -> x.setPosition(pos));
    }
    @Override
    public boolean isInCheck(final Chessboard chessboard, final Color color) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(color))
                .anyMatch(x -> canEatKing(chessboard, x));
    }

    private boolean canEatKing(final Chessboard chessboard, final Piece piece) {
        final Optional<Position> pos = getEnemyKingPosition(chessboard, piece);
        return pos.isPresent() && piece.getAllPossiblePositions(chessboard).contains(pos.get());
    }
    private Optional<Position> getEnemyKingPosition(final Chessboard chessboard, final Piece piece) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(piece.getColor()))
                .filter(x -> x.getName().equals(KING))
                .findFirst()
                .map(x -> Optional.of(x.getPosition()))
                .orElse(Optional.empty());
    }



}
