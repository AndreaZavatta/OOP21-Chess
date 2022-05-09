package board;

import static piece.utils.Name.KING;
import piece.utils.Color;
import piece.utils.Move;

import java.util.ArrayList;
import java.util.List;

import piece.utils.Position;
import exceptions.KingNotFoundException;
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
        return isInCheck(simulateMove(chessboard, piece.getPosition(), pos), piece.getColor());
    }
    private Chessboard simulateMove(final Chessboard chessboard, final Position startPos, final Position destPos) {
        final ChessboardImpl chessboardCopy = (ChessboardImpl) copyChessboard(chessboard);
        chessboardCopy.getAllPieces().stream()
        .filter(x -> x.getPosition().equals(startPos))
        .findFirst()
        .ifPresent(x -> chessboardCopy.moveWithoutChecks(x, destPos));
        return chessboardCopy;
    }
    private Chessboard copyChessboard(final Chessboard chessboard) {
        return chessboardFact.createTestCB(chessboard.getAllPieces());
    }
    @Override
    public boolean isInCheck(final Chessboard chessboard, final Color color) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(color))
                .anyMatch(x -> canEatKing(chessboard, x));
    }

    private boolean canEatKing(final Chessboard chessboard, final Piece piece) {
        return piece.getAllPossiblePositions(chessboard)
                .contains(getEnemyKingPosition(chessboard, piece));
    }
    private Position getEnemyKingPosition(final Chessboard chessboard, final Piece piece) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(piece.getColor()))
                .filter(x -> x.getName().equals(KING))
                .findFirst()
                .map(Piece::getPosition)
                .orElseThrow(KingNotFoundException::new);
    }
}
