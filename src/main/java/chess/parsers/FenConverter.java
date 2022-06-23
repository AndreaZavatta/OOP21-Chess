package chess.parsers;

import model.board.Chessboard;

/**
 * Interface for converting a Chessboard in a string fen.
 *
 */
public interface FenConverter {
    /**
     * function that deserialize from fen string to Chessboard object.
     * @param fen the string to deserialize
     * @return Chessboard the chessboard that represent the string
     */
    Chessboard getBoard(String fen);
}
