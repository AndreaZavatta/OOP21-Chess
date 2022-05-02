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
public class Bishop extends AbstractPiece {

    private static final int BISHOP_VALUE = 3;

    /**
     * A Bishop piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Bishop(final Position position, final Color color) {
        super(Name.BISHOP, position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Piece piece, final Chessboard board) {
        final List<Position> list = new ArrayList<>();
        for (final var pos : Name.BISHOP.directions()) {
            for (int i = 1; i < 8; i++) {
                final Position p = this.getNewPosition(piece, pos, i);
                if (this.checkPiece(p, board)) {
                    if (this.checkEnemy(p, board)) {
                        list.add(p);
                    } 
                    break;
                } else if (this.checkPosition(p)) {
                    list.add(p);
                } else {
                    break;
                }
            }
        }
        return Collections.unmodifiableList(list);
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

    @Override
    public int getValue() {
        return BISHOP_VALUE;
    }
}
