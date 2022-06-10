package controller.utils;

import javafx.scene.paint.Color;
/**
 * An enum used to handle the game theme.
 */
public enum ColorSettings {
    /**
     * 
     */
    CORAL(Color.web("b7b7a4"), Color.web("616E59"),
            Color.web("#b1e4b9"), Color.web("#70a2a3"), Color.WHITE, Color.YELLOW),
    /**
     * 
     */
    STANDARD(Color.web("d4a373"), Color.web("#c08552"),
            Color.valueOf("#582"), Color.valueOf("#feb"), Color.YELLOW, Color.RED);

    private final Color background;
    private final Color pieceEffect;
    private final Color r1;
    private final Color r2;
    private final Color rectangleEffect;
    private final Color boxesColor;

    ColorSettings(final Color background, final Color boxesColor, final Color r1,
            final Color r2, final Color rectangleEffect, final Color pieceEffect) {
        this.background = background;
        this.pieceEffect = pieceEffect;
        this.r1 = r1;
        this.r2 = r2;
        this.rectangleEffect = rectangleEffect;
        this.boxesColor = boxesColor;
    }
    /**
     * A getter for the background color.
     * @return the background color.
     */
    public Color getBackground() {
        return background;
    }
    /**
     * A getter for the piece effect color.
     * @return the color of the piece effect.
     */
    public Color getPieceEffect() {
        return pieceEffect;
    }
    /**
     * A getter for the color of half rectangles.
     * @return the color of half rectangles.
     */
    public Color getR1() {
        return r1;
    }
    /**
     * A getter for the color of half rectangles.
     * @return the color of half rectangles.
     */
    public Color getR2() {
        return r2;
    }
    /**
     * A getter for the rectangle effect color.
     * @return the color of the rectangle effect.
     */
    public Color getRectangleEffect() {
        return rectangleEffect;
    }
    /**
     * A getter for the bottom and left pane color.
     * @return the background color of the panes.
     */
    public Color getBoxesColor() {
        return boxesColor;
    }
}
