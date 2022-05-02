package board;

import java.util.List;

import pieces.Piece;

/**
     * 
     * interface for ChessboardFactory.
     *
     */
public interface ChessboardFactory {

    /**
     * 
     * @return a normal chess
     */
    Chessboard createNormalCB();

    /**
     * 
     * @return a chess ONLY for tests
     */
    Chessboard createTestCB(List<Piece> piecesOnBoard);
}
