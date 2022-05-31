package model.move;

import java.util.List;

import board.Chessboard;
import model.piece.utils.PieceDirections;
import model.piece.utils.Position;
import model.pieces.Piece;
/**
 * This interface contains methods used by the pieces in order to
 * generate the list of possible positions.
 */
public interface BasicMoves {
    /**
     * This method is used by the Rook, Bishop and Queen in order to
     * return the list of possible position. It iterates for the piece's direction and for the entire board.
     * 
     * @param piece the piece.
     * @param board the current board.
     * @param directions the piece's directions.
     * @return a list of position the piece can go to.
     */
    List<Position> iteratedMove(PieceDirections directions, Chessboard board, Piece piece);
    /**
     * This method is used by the King and Knight in order to 
     * return the list of possible position. It iterates only for the piece's direction since for those pieces
     * you don't need to iterate for the entire board.
     * 
     * @param directions the piece's directions.
     * @param board the current board.
     * @param piece the piece.
     * @return a list of position the piece can go to.
     */
    List<Position> directMove(PieceDirections directions, Chessboard board, Piece piece);
}
