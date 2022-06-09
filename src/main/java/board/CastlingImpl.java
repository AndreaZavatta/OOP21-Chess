package board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;

/**
 *
 * Implementation of Castling Interface's methods.
 *
 */
public class CastlingImpl implements Castling {

    private final ControlCheck kingInCheck = new ControlCheckImpl();

    @Override
    public boolean canCastle(final Chessboard chessboard, final Piece king, final int xPos) {
        return chessboard.getPieceOnPosition(Position.createNumericPosition(xPos, king.getPosition().getY()))
                .map(r -> isCastlePossible(chessboard, r, king))
                .orElse(false);
    }

    private boolean isCastlePossible(final Chessboard chessboard, final Piece rook, final Piece king) {

        // we're working with the correct pieces;
        if (!king.getName().equals(Name.KING) || !rook.getName().equals(Name.ROOK)) {
            return false;
        }

        // the pieces must not have been moved;
        if (king.isMoved() || rook.isMoved()) {
            return false;
        }

        if (kingInCheck.isInCheckWithoutKing(chessboard, king.getSide())) {
            return false;
        }

        final var positions = horizontalPositionsBetweenPieces(rook, king);

        // the list must contain only non-occupied positions;
        if (positions.stream().anyMatch(p -> chessboard.getPieceOnPosition(p).isPresent())) {
            return false;
        }

        // filter the previous list and get the positions the king has to go through;
        return positions.stream()
                .filter(p -> Math.abs(king.getPosition().getX() - p.getX()) <= 2)
                .noneMatch(p -> isPositionUnderAttack(chessboard, p, king.getSide()));
    }

    private boolean isPositionUnderAttack(final Chessboard chessboard, final Position position, final Side attackedSide) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getSide().equals(attackedSide))
                .filter(x -> !x.getName().equals(Name.KING))
                .anyMatch(p -> p.getAllPossiblePositions(chessboard).contains(position));
    }

    private List<Position> horizontalPositionsBetweenPieces(final Piece piece1, final Piece piece2) {
        final int y = piece1.getPosition().getY();
        final int x1 = piece1.getPosition().getX();
        final int x2 = piece2.getPosition().getX();

        int maxX, minX;
        if (x1 > x2) {
            maxX = x1;
            minX = x2;
        } else {
            minX = x1;
            maxX = x2;
        }
        return IntStream.range(minX + 1, maxX).mapToObj(x -> Position.createNumericPosition(x, y)).collect(Collectors.toUnmodifiableList());
    }
}
