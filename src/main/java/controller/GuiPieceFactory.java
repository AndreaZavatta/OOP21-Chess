package controller;

import model.pieces.Piece;
import view.GuiPiece;

/**
 * 
 * Simple interface for GuiPieceFactoryImpl.
 *
 */
public interface GuiPieceFactory {

    /**
     * It create a guiPiece from an input piece.
     * It's useful for view and controller.
     * @param piece piece of the chessboard
     * @return a guiPiece of the input piece
     */
    GuiPiece createGuiPiece(Piece piece);
}
