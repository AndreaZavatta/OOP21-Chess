package promotion;

import javafx.fxml.FXML;
import piece.utils.Name;
import pieces.Piece;
/**
 * Pawn promotion controller class.
 *
 */
public class PawnPromotionController {
    private final PromotionImpl promotion = new PromotionImpl();
    private final Piece piece;
    /**
     * 
     * @param piece
     */
    public PawnPromotionController(final Piece piece) {
        this.piece = piece;
    }
    @FXML
    public void replaceToQueen() {
        promotion.changePiece(Name.QUEEN, piece);
    }
    @FXML
    public void replaceToBishop() {
        promotion.changePiece(Name.BISHOP, piece);
    }
    @FXML
    public void replaceToRook() {
        promotion.changePiece(Name.ROOK, piece);
    }
    @FXML
    public void replaceToKnight() {
        promotion.changePiece(Name.KNIGHT, piece);
    }
}
