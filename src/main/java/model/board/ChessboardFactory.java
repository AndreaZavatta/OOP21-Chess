package model.board;

import java.util.List;

import model.pieces.Piece;

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
     * @param piecesOnBoard
     * @return a custom chessboard for tests
     */
    Chessboard createTestCB(List<Piece> piecesOnBoard);

    /**
     * 
     * @param fenString
     * @return a custom CB from a string
     */
    Chessboard createCBToFen(String fenString);
}
