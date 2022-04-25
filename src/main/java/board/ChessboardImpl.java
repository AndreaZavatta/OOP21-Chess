package board;

import java.util.List;

import piece.utils.Position;
import pieces.Piece;

class ChessboardImpl implements Chessboard {
    public static final int X = 8;
    public static final int Y = 8;

    private List<Piece> piecesList;

    ChessboardImpl(final List<Piece> piecesList) {
        this.piecesList = piecesList;
    }

    @Override
    public List<Piece> getAllPieces() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean move(final Position actualPos, final Position finalPos) {
        // TODO Auto-generated method stub
        return false;
    }
}
