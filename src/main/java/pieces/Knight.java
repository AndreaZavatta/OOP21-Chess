package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;

/**
 * 
 *
 */
public final class Knight extends AbstractPiece {

    private static final int KNIGHT_VALUE = 3;

    /**
     * A Knight piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Knight(final Position position, final Color color) {
        super(Name.KNIGHT, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Piece piece, final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        for (final var pos: Name.KNIGHT.directions()) {
            final Position p = this.getNewPosition(piece, pos, 1);
            if (this.checkPosition(p)) {
                addPiece(board, list, p);
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getValue() {
        return KNIGHT_VALUE;
    }

    private void addPiece(final Chessboard board, final List<Position> list, final Position p) {
        if (this.checkPiece(p, board) || checkEnemy(p, board)) {
            list.add(p);
        }
    }

    private boolean checkEnemy(final Position position, final Chessboard board) {
        final Color s = board.getAllPieces().stream()
                .filter(x -> x.getPosition().equals(position))
                .map(x -> x.getColor())
                .findFirst().get();
        return board.getAllPieces().stream()
                .filter(x -> !this.getColor().equals(s))
                .map(Piece::getPosition)
                .collect(Collectors.toList())
                .contains(position);
    }

    private boolean checkPiece(final Position position, final Chessboard board) {
        return board.getAllPieces().stream().map(Piece::getPosition).collect(Collectors.toList()).contains(position);
    }

    private boolean checkPosition(final Position position) {
        return position.getX() < 8 && position.getX() >= 0 
                && position.getY() < 8 && position.getY() >= 0;
    }

    private Position getNewPosition(final Piece piece, final Position position, final int multiplier) {
        return new Position(piece.getPosition().getX() + (position.getX() * multiplier),
                piece.getPosition().getY() + (position.getY() * multiplier));
    }
}
