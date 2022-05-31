package promotion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import model.promotion.Promotion;
import model.promotion.PromotionImpl;
import org.junit.jupiter.api.Test;

import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

class PromotionImplTest {

    private final Promotion prom = new PromotionImpl();
    private final PieceFactory factory = new PieceFactoryImpl();
    //private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void test() {
        final List<Piece> list = new ArrayList<>();
        final Piece pawn = factory.createPiece(Name.PAWN, Position.createNumericPosition(0, 6), Side.BLACK);
        list.add(pawn);
        assertFalse(prom.checkForPromotion(list).isPresent());
        pawn.setPosition(Position.createNumericPosition(0, Numbers.SEVEN));
        assertTrue(prom.checkForPromotion(list).isEmpty());
    }

}
