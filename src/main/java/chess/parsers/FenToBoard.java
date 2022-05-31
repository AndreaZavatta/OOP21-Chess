package chess.parsers;

import board.Chessboard;

public interface FenToBoard {
    Chessboard getBoard(String fen);
}
