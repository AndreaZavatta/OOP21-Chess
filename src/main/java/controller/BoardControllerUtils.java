package controller;

import java.util.Map;

import game.Game;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.pieces.Piece;

/**
 * 
 *
 */
public final class BoardControllerUtils {

    private static final double CHOKE_VALUE = 0.8;

    private BoardControllerUtils() { }
    /**
     * 
     * @param match
     * @param mapGuiPieceToPiece
     * @return the king of this turn.
     */
    public static GuiPiece getKingOfThisTurn(final Game match, final Map<GuiPiece, Piece> mapGuiPieceToPiece) {
        final var king = match.getPiecesList().stream()
                .filter(a -> a.getName().equals(Name.KING))
                .filter(a -> a.getSide().equals(match.getUserSideTurn()))
                .findFirst().get();
        final var pos = king.getPosition();
        return mapGuiPieceToPiece.keySet().stream()
                .filter(b -> b.getPosition().equals(pos))
                .findFirst().get();
    }
    /**
     * 
     * @param match
     * @param mapGuiPieceToPiece
     * @return the king of the other turn.
     */
    public static GuiPiece getKingOfTheOtherTurn(final Game match, final Map<GuiPiece, Piece> mapGuiPieceToPiece) {
        final var king = match.getPiecesList().stream()
                .filter(a -> a.getName().equals(Name.KING))
                .filter(a -> !a.getSide().equals(match.getUserSideTurn()))
                .findFirst().get();
        final var pos = king.getPosition();
        return mapGuiPieceToPiece.keySet().stream()
                .filter(b -> b.getPosition().equals(pos))
                .findFirst().get();
    }
    /**
     * 
     * @param color
     * @param rectangle
     */
    public static void setEffect(final Color color, final Rectangle rectangle) {
        final InnerShadow shadow = new InnerShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setRadius(Numbers.SEVEN);
        shadow.setChoke(CHOKE_VALUE);
        shadow.setColor(color);
        rectangle.setEffect(shadow);
    }
}
