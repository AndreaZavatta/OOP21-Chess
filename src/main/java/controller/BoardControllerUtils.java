package controller;

import java.util.Map;

import game.Game;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
     * A getter for the king of this turn.
     * @param match the current match.
     * @param mapGuiPieceToPiece the current map of GuiPiece-Piece.
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
     * A getter for the king of the opposite turn.
     * @param match the current match.
     * @param mapGuiPieceToPiece the current map of GuiPiece-Piece.
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
     * A setter for the effects of the rectangles.
     * Also used when the king is on check to show it.
     * @param color the color of the effects.
     * @param rectangle the affected rectangle.
     */
    public static void setEffect(final Color color, final Rectangle rectangle) {
        final InnerShadow shadow = new InnerShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setRadius(Numbers.SEVEN);
        shadow.setChoke(CHOKE_VALUE);
        shadow.setColor(color);
        rectangle.setEffect(shadow);
    }

    /**
     * A method for removing the effects of the rectangles.
     * If the king is on check, it's effect will not be removed.
     * @param rectangle the rectangle.
     * @param match the current match.
     * @param mapGuiPieceToPiece the current map GuiPiece-Piece.
     */
    public static void removeEffect(final Rectangle rectangle, final Game match, final Map<GuiPiece, Piece> mapGuiPieceToPiece) {
        if (!rectangle.equals(BoardControllerUtils.getKingOfThisTurn(match, mapGuiPieceToPiece).getRectangle())
                || !match.isInCheck()) {
            rectangle.setEffect(null);
        }
    }
    /**
     * A method for setting effects to the image and name of the player who is moving the pieces.
     * It colors the name red and lower the brightness of the other player's image.
     * @param text the name of the player who is moving the pieces.
     * @param image the image of the other player.
     */
    public static void setEffectPlayerTurn(final Text text, final ImageView image) {
        text.setStyle("-fx-font: 20 arial;");
        text.setFill(Color.RED);
        final ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        image.setEffect(colorAdjust);
    }
    /**
     * A method used to remove any effect from an image and a text.
     * @param text the text you are removing the effect.
     * @param image the image you are removing the effect.
     */
    public static void removeEffects(final Text text, final ImageView image) {
        image.setEffect(null);
        setTextOptions(text);
    }
    /**
     * A setter for the color, font and dimension of the text.
     * @param text the text.
     */
    public static void setTextOptions(final Text text) {
        text.setStyle("-fx-font: 18 arial;");
        text.setFill(Color.WHITE);
    }
}
