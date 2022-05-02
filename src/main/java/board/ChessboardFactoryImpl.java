package board;

import java.util.List;

import pieces.Piece;

/**
     * 
     * This class create different types of chessboard
     * for different types of game modes.
     *
     */
public class ChessboardFactoryImpl implements ChessboardFactory {

    @Override
    public Chessboard createNormalCB() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chessboard createTestCB(final List<Piece> piecesOnBoard) {
        return new ChessboardImpl(piecesOnBoard, 8, 8);
    }

}
