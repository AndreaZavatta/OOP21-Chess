package controller.utils;


import javafx.scene.paint.Color;
/**
 * An enum used to handle the game theme.
 */
public enum ColorSettings {
    /**
     * 
     */
    CORAL(Color.web("b7b7a4"), Color.web("616E59"), Color.web("#b1e4b9"), Color.web("#70a2a3"), Color.BLUEVIOLET, Color.WHITE, Color.WHITE),
    /**
     * 
     */
    STANDARD(Color.web("d4a373"), Color.web("#c08552"), Color.valueOf("#582"), Color.valueOf("#feb"), Color.WHITE, Color.YELLOW, Color.RED);

    final Color background;
    final Color textColor;
    final Color hoverEffect;
    final Color r1;
    final Color r2;
    final Color rectangleEffect;
    final Color boxesColor;

    ColorSettings(final Color background, final Color boxesColor, final Color r1,
    final Color r2, final Color textColor, final Color rectangleEffect, final Color hoverEffect) {
        this.background = background;
        this.textColor = textColor;
        this.hoverEffect = hoverEffect;
        this.r1 = r1;
        this.r2 = r2;
        this.rectangleEffect = rectangleEffect;
        this.boxesColor = boxesColor;
    }

    public Color getBackground() {
        return background;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getHoverEffect() {
        return hoverEffect;
    }

    public Color getR1() {
        return r1;
    }

    public Color getR2() {
        return r2;
    }

    public Color getRectangleEffect() {
        return rectangleEffect;
    }

    public Color getBoxesColor() {
        return boxesColor;
    }
}
