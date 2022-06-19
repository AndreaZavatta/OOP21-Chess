package controller;

import java.util.Map;

import model.game.Game;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
     * If the rectangle is not the king's rectangle, or if the king is not in check, then remove the effect from the
     * rectangle
     *
     * @param rectangle the rectangle of the piece that is being checked
     * @param match the game object
     * @param mapGuiPieceToPiece a map that maps a GuiPiece to a Piece.
     */
    public static void removeEffect(final Rectangle rectangle, final Game match,
                                    final Map<GuiPiece, Piece> mapGuiPieceToPiece) {
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
     * Removes the effects from the image and sets the text options.
     *
     * @param text The Text object that you want to apply the effect to.
     * @param image The ImageView that you want to apply the effect to.
     */
    public static void removeEffects(final Text text, final ImageView image) {
        image.setEffect(null);
        setTextOptions(text);
    }
    /**
     * Sets the style and color of the text.
     *
     * @param text The text object that you want to set the options for.
     */
    public static void setTextOptions(final Text text) {
        text.setStyle("-fx-font: 18 arial;");
        text.setFill(Color.WHITE);
    }
    /**
     * This method returns a Background object with a BackgroundFill object that has a theme color,
     * null insets, and null radius.
     *
     * @param theme The color of the background
     * @return A Background object with a BackgroundFill object as its parameter.
     */
    public static Background getBackground(final Color theme) {
        return new Background(new BackgroundFill(theme, null, null));
    }
}
