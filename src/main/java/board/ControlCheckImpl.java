package board;

import static piece.utils.Name.KING;
import piece.utils.Side;
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
    public List<Position> controlledMoves(final Chessboard chessboard, final Piece piece) {
        final List<Position> avaliableMoves = new ArrayList<>(piece.getAllPossiblePositions(chessboard));
        avaliableMoves.removeIf(x -> isMoveInCheck(chessboard, piece, x));
        return avaliableMoves;
    }

    private boolean isMoveInCheck(final Chessboard chessboard, final Piece piece, final Position pos) {
        return isInCheck(simulateMove(chessboard, piece, pos), piece.getColor());
    }
    private Chessboard simulateMove(final Chessboard chessboard, final Piece piece, final Position destPos) {
        final ChessboardImpl chessboardCopy = copyChessboard(chessboard);
        chessboardCopy.getAllPieces().stream().filter(x -> x.getPosition().equals(piece.getPosition()))
        .findFirst()
        .ifPresent(x -> chessboardCopy.moveWithoutChecks(x, destPos));
        return chessboardCopy;
    }

    private ChessboardImpl copyChessboard(final Chessboard chessboard) {
        return (ChessboardImpl) chessboardFact.createTestCB(chessboard.getAllPieces());
    }
    @Override
    public boolean isInCheck(final Chessboard chessboard, final Side color) {
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
