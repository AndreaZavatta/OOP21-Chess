package model.move;

import java.util.List;

import model.board.Chessboard;
import model.piece.utils.PieceDirections;
import model.piece.utils.Position;
import model.pieces.Piece;
/**
 * This interface contains methods used by the pieces in order to
 * generate the list of possible positions.
 */
public interface PieceMovement {
    /**
     * Given a {@link model.pieces.Piece}, a {@link model.board.Chessboard},
     * and a set of {@link model.piece.utils.PieceDirections},
     * return a list of all the positions that the piece can move
     * to. The function takes in a set of directions,
     * which is a list of directions that the piece can move in.
     *
     * @param directions The directions the piece can move in.
     * @param board The chessboard that the piece is on.
     * @param piece The piece that is moving
     * @return A list of positions that the piece can move to.
     */
    List<Position> multipleMove(PieceDirections directions, Chessboard board, Piece piece);
    /**
     * This method is used by the {@link model.pieces.King} and {@link model.pieces.Knight} in order to
     * return the list of possible position. It iterates only for the piece's direction since for those pieces
     * you don't need to iterate for the entire board.
     *
     * @param directions the piece's directions.
     * @param board the current board.
     * @param piece the piece.
     * @return a list of position the piece can go to.
     */
    List<Position> singleMove(PieceDirections directions, Chessboard board, Piece piece);
}
