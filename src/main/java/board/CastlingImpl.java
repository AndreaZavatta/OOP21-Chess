package board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import piece.utils.Name;
import piece.utils.Numbers;
import piece.utils.Position;
import piece.utils.Side;
import pieces.King;
import pieces.Piece;
import pieces.Rook;

/**
 * 
 * 
 *
 */
public class CastlingImpl implements Castling {

    public Optional<Position> canCastle1(final Chessboard chessboard, final Side side, final int xPos) {
        final var king = chessboard.getAllPieces().stream()
                .filter(x -> x.getName().equals(Name.KING))
                .filter(x -> x.getSide().equals(side))
                .findFirst()
                .get();
        if (chessboard.getPieceOnPosition(Position.createNumericPosition(xPos, king.getPosition().getY()))
                .map(r -> isCastlePossible(chessboard, r, king))
                .orElse(false)) {
            return Optional.of(king.getPosition());
        }
        return Optional.empty();
    }

    @Override
    public boolean canCastle(final Chessboard chessboard, final Side side, final int xPos) {
        final var king = chessboard.getAllPieces().stream()
                .filter(x -> x.getName().equals(Name.KING))
                .filter(x -> x.getSide().equals(side))
                .findFirst()
                .get();
        return chessboard.getPieceOnPosition(Position.createNumericPosition(xPos, king.getPosition().getY()))
                .map(r -> isCastlePossible(chessboard, r, king))
                .orElse(false); 
    }

    private boolean isCastlePossible(final Chessboard chessboard, final Piece rook, final Piece king) {

        //conferma pezzi
        if (!king.getName().equals(Name.KING) || !rook.getName().equals(Name.ROOK)) {
            return false;
        }

        // re e torre interessata non mossi
        if (king.isMoved() || rook.isMoved()) {
            return false;
        }

        final var positions = horizontalPositionsBetweenPieces(rook, king);

        // la lista deve contenere posizioni non occupate
        if (positions.stream().anyMatch(p -> chessboard.getPieceOnPosition(p).isPresent())) {
            return false;
        }

        // lista delle case attraversate dal re 
        if (positions.stream()
                .filter(p -> Math.abs(king.getPosition().getX() - p.getX()) <= 2)
                .anyMatch(p -> isPositionUnderAttack(chessboard, p, king.getSide()))) {
            return false;
        }

        return true;
    }

    private boolean isPositionUnderAttack(final Chessboard chessboard, final Position position, final Side attackedSide) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getSide().equals(attackedSide))
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
