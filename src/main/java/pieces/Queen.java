package pieces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Side;
import piece.utils.Name;

/**
 * A Queen class that extends AbstractPiece abstract class.
 *
 */
public class Queen extends AbstractPiece {

    private static final int QUEEN_VALUE = 9;
    private final Bishop bishop;
    private final Rook rook;

    /**
     * A Queen piece constructor.
     * 
     * @param position the piece position.
     * @param color the piece color.
     */
    protected Queen(final Position position, final Side color) {
        super(Name.QUEEN, position, color);
        this.bishop = new Bishop(position, color);
        this.rook = new Rook(position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Chessboard board) {
        this.updatePosition();
        return Stream.concat(bishop.getAllPossiblePositions(board).stream(), 
                rook.getAllPossiblePositions(board).stream()).collect(Collectors.toList());
    }

    @Override
    public int getValue() {
        return QUEEN_VALUE;
    }

    private void updatePosition() {
        this.bishop.setPosition(this.getPosition());
        this.rook.setPosition(this.getPosition());
    }

}
