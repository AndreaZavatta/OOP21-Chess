package board;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import piece.utils.Position;
import pieces.Piece;

    /**
     * 
     * a normal chessboard.
     *
     */
class ChessboardImpl implements Chessboard {
    private List<Piece> piecesList;
    private final int xBorder;
    private final int yBorder;

    ChessboardImpl(final List<Piece> piecesList, final int yBorder, final int xBorder) {
        this.piecesList = piecesList;
        this.xBorder = xBorder;
        this.yBorder = yBorder;
    }

    @Override
    public List<Piece> getAllPieces() {
        return Collections.unmodifiableList(this.piecesList);
    }

    @Override
    public void move(final Position actualPos, final Position finalPos) {
        // TODO Auto-generated method stub
        return;
    }

    public int getxBorder() {
        return this.xBorder;
    }

    public int getyBorder() {
        return this.yBorder;
    }

    @Override
    public String toString() {
        String pieces = this.piecesList.stream().map(Piece::toString)
                                               .collect(Collectors.joining(" | "));
        return "Chessboard: " + pieces;
    }
}
