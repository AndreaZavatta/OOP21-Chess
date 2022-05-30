package gui.board;

import pieces.Piece;

interface GuiPieceFactory {

    GuiPiece createGuiPiece(Piece piece);
}
