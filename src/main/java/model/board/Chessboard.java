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
     * 
     * @return a list of all pieces on the board
     */
    List<Piece> getAllPieces();

    /**
     * 
     * @param actualPos
     * @param finalPos 
     */
    void move(Position actualPos, Position finalPos);

    /**
     * 
     * @return X border of chessboard
     */
    int getxBorder();

    /**
     * 
     * @return Y border of chessboard
     */
    int getyBorder();

    /**
     * 
     * @param selectedPos
     * @return an optional of a piece
     */
    Optional<Piece> getPieceOnPosition(Position selectedPos);

    /**
     * 
     * @param attacker
     * @return a list of all possible position
     */
    List<Position> getAllPosition(Piece attacker);

    /**
     * 
     * @param namePiece
     * 
     * pawn get promoted to a new Piece.
     * @return the new piece
     */
    Piece promotion(Name namePiece);

    /**
     * 
     * @param piece
     * @param targetPos
     * @return true if king is castling
     */
    boolean isCastling(Piece piece, Position targetPos);
}
