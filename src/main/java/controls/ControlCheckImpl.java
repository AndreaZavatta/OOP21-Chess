package controls;

import static piece.utils.Name.KING;
import piece.utils.Color;
import piece.utils.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import piece.utils.Position;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import exceptions.KingNotFoundException;
import pieces.Piece;

/**
 * 
 *
 */

public class ControlCheckImpl implements ControlCheck {
  private final ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    @Override
    public List<Position> removeMovesInCheck(final Chessboard chessboard, final Piece piece, final Consumer<Move> move) {
        final List<Position> avaliableMoves = new ArrayList<>(piece.getAllPossiblePositions(chessboard));
        avaliableMoves.removeIf(x -> this.isMoveInCheck(chessboard, piece, x, move));
        return avaliableMoves;
    }

    private boolean isMoveInCheck(final Chessboard chessboard, final Piece piece, final Position pos, final Consumer<Move> move) {
        return isInCheck(simulateMove(chessboard, piece, pos, move), piece.getColor());
    }
    private Chessboard simulateMove(final Chessboard chessboard, final Piece piece, final Position destPos, final Consumer<Move> move) {
        final Chessboard chessboardCopy = copyChessboard(chessboard);
        move.accept(new Move(piece.getPosition(), destPos, chessboard));
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
