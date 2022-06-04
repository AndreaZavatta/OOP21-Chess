package chess.parsers;

import board.Chessboard;

public interface FenConverter {
    Chessboard getBoard(String fen);
}
