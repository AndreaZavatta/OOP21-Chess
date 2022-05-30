package controller;

import model.pieces.Piece;

interface GuiPieceFactory {

    GuiPiece createGuiPiece(Piece piece);
}
