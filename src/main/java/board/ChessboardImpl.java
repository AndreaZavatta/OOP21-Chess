package board;

import java.util.Collections;
import java.util.List;

import piece.utils.Position;
import pieces.Piece;

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
    public boolean move(final Position actualPos, final Position finalPos) {
        // TODO Auto-generated method stub
        return false;
    }

    public int getxBorder() {
        return this.xBorder;
    }

    public int getyBorder() {
        return this.yBorder;
    }
}
