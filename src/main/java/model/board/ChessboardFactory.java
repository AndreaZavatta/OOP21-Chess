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
     * Create a standard chessboard for games.
     * @return a standard chess
     */
    Chessboard createNormalCB();

    /**
     * From a list of piece it create a chessboard,
     * mainly use for tests.
     * @param piecesOnBoard a list of piece
     * @return a custom chessboard
     */
    Chessboard createTestCB(List<Piece> piecesOnBoard);

    /**
     * From a fenString it create a chessboard,
     * mainly use for tests.
     * @param fenString a fen string auto generated
     * @return a custom CB from a tests
     */
    Chessboard createCBToFen(String fenString);
}
