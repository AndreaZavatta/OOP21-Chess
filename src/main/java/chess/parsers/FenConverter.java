package chess.parsers;

import model.board.Chessboard;

/**
 * interface for converting a Chessboard in a string fen
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
