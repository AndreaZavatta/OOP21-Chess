package pieces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import board.Chessboard;
import piece.utils.Position;
import piece.utils.Color;
import piece.utils.Name;

/**
 * 
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
    protected Queen(final Position position, final Color color) {
        super(Name.QUEEN, position, color);
        this.bishop = new Bishop(position, color);
        this.rook = new Rook(position, color);
    }

    @Override
    public List<Position> getAllPossiblePositions(final Piece piece, final Chessboard board) {
        return Stream.concat(bishop.getAllPossiblePositions(piece, board).stream(), 
                rook.getAllPossiblePositions(piece, board).stream()).collect(Collectors.toList());
    }

    @Override
    public int getValue() {
        return QUEEN_VALUE;
    }

}
