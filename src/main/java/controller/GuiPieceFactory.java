package controller;

import model.pieces.Piece;

/**
 * 
 * Simple interface for GuiPieceFactoryImpl.
 *
 */
public interface GuiPieceFactory {

    /**
     * It create a guiPiece from an input piece.
     * It's useful for view and controller.
     * @param piece
     * @return a guiPiece of the input piece
     */
    GuiPiece createGuiPiece(Piece piece);
}
