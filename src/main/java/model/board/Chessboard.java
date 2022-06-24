package model.board;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;

    /**
     * Interface for a simple chessboard. 
     *
     */
    @JsonDeserialize(as = ChessboardImpl.class)
public interface Chessboard {

    /**
     * Return all pieces in chessboard.
     * @return a list of all pieces on the board
     */
    List<Piece> getAllPieces();

    /**
     * Move a piece to a position to an another one.
     * @param actualPos the initial position of the piece
     * @param finalPos the position where the piece want to move
     */
    void move(Position actualPos, Position finalPos);

    /**
     * This method is useful for pieces to 
     * calculate their possible moves.
     * @return X chessboard's border
     */
    int getxBorder();

    /**
     * This method is useful for pieces to 
     * calculate their possible moves.
     * @return Y chessboard's border
     */
    int getyBorder();

    /**
     * Try to get a piece from a position if it exist.
     * @param selectedPos The position to check
     * @return an optional of piece
     */
    Optional<Piece> getPieceOnPosition(Position selectedPos);

    /**
     * From a piece it return all its allowed position.
     * @param attacker every piece is allowed
     * @return a list of all possible position
     */
    List<Position> getAllPosition(Piece attacker);

    /**
     * Pawn get promoted to a new Piece.
     * @param namePiece the name of the new piece
     * @return the new piece
     */
    Piece promotion(Name namePiece);

    /**
     * From a piece and a position it checks if 
     * the piece is castling.
     * @param piece the king
     * @param targetPos the position where the king want to move
     * @return true if king is castling
     */
    boolean isCastling(Piece piece, Position targetPos);
}
