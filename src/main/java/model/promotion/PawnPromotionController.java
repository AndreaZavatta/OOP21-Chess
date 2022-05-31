package model.promotion;

import model.piece.utils.Name;
import model.pieces.Piece;
/**
 * Pawn promotion controller class.
 */
public class PawnPromotionController {
    private final Promotion promotion = new PromotionImpl();
    private final Piece piece;
    /**
     * A standard constructor.
     * @param piece the piece you will remove.
     */
    public PawnPromotionController(final Piece piece) {
        this.piece = piece;
    }
    /**
     * @FXML
     * Method to replace the pawn to a Queen.
     */
    public void replaceToQueen() {
        promotion.changePiece(Name.QUEEN, piece);
    }
    /**
     * @FXML
     * Method to replace the pawn to a Bishop.
     */
    public void replaceToBishop() {
        promotion.changePiece(Name.BISHOP, piece);
    }
    /**
     * @FXML
     * Method to replace the pawn to a Rook.
     */
    public void replaceToRook() {
        promotion.changePiece(Name.ROOK, piece);
    }
    /**
     * @FXML
     * Method to replace the pawn to a Knight.
     */
    public void replaceToKnight() {
        promotion.changePiece(Name.KNIGHT, piece);
    }
}
