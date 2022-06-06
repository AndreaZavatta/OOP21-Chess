package chess.parsers;

import board.Chessboard;

/**
 * interface for FenConverter.
 *
 */
public interface FenConverter {
    /**
     * function that deserialize from fen string to Chessboard object.
     * @param fen
     * @return Chessboard
     */
    Chessboard getBoard(String fen);
}
