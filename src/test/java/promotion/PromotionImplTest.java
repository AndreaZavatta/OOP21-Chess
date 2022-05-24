package promotion;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import piece.utils.Name;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

class PromotionImplTest {

    //private final Promotion prom = new PromotionImpl();
    private final PieceFactory factory = new PieceFactoryImpl();
    //private final ChessboardFactory board = new ChessboardFactoryImpl();

    @Test
    void test() {
        final List<Piece> list = new ArrayList<>();
        final Piece pawn = factory.createPiece(Name.PAWN, Position.createNumericPosition(0, 6), Side.WHITE);
        list.add(pawn);
        //assertFalse(prom.checkForPromotion(list));
        pawn.setPosition(Position.createNumericPosition(0, 0));
        //assertTrue(prom.checkForPromotion(list));
    }

}
