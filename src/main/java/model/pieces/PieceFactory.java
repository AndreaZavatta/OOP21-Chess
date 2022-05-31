package model.pieces;

import model.piece.utils.Position;
import model.piece.utils.Side;
import model.piece.utils.Name;

/**
 * A standard interface for a Piece Factory.
 */
public interface PieceFactory {
    /**
     * A standard method to create a Piece. Also used to promote a pawn into another piece.
     * 
     * @param name the name of the piece.
     * @param position the position of the piece.
     * @param color the color of the piece.
     * @return given a name, return a Piece object.
     */
    Piece createPiece(Name name, Position position, Side color);
}