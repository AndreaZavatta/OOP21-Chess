package gui.board;

import java.util.Map;

import gui.board.utils.PieceImagePath;
import piece.utils.Name;
import piece.utils.Side;
import pieces.Piece;

class GuiPieceFactoryImpl implements GuiPieceFactory {

    private static final Map<Name, PieceImagePath> LINK = Map.of(
            Name.PAWN, PieceImagePath.PAWN,
            Name.QUEEN, PieceImagePath.QUEEN,
            Name.KING, PieceImagePath.KING,
            Name.KNIGHT, PieceImagePath.KNIGHT,
            Name.ROOK, PieceImagePath.ROOK,
            Name.BISHOP, PieceImagePath.BISHOP
            );

    @Override
    public GuiPiece createGuiPiece(final Piece piece) {
        final String path = piece.getSide().equals(Side.WHITE)
                        ? LINK.get(piece.getName()).getWhitePath() 
                        : LINK.get(piece.getName()).getBlackPath();
        return new GuiPiece(BoardController.TILE_SIZE, BoardController.TILE_SIZE, path);
    }
}
