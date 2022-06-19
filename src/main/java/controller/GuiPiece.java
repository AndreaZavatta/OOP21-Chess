package controller;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.piece.utils.Position;

import static controller.BoardController.TILE_SIZE;

/**
 * The GuiPiece class defines the piece you see on the chessboard.
 * It contains it's size, location and the Rectangle associated.
 */
public class GuiPiece {
    private double x;
    private double y;
    private final Rectangle rectangle;
    /**
     * A standard constructor.
     *
     * @param s the rectangle.
     * @param x the x value.
     * @param y the y value.
     */
    protected GuiPiece(final double x, final double y, final String s) {
        this.rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
        final Image im = new Image(s, TILE_SIZE, TILE_SIZE, true, false);
        rectangle.setFill(new ImagePattern(im));
        this.x = x;
        this.y = y;
    }
    /**
     * A custom toString for the position.
     * @return the position.
     */
    public String getPositionToString() {
        return getPosition().toString();
    }
    /**
     * A setter for the x value.
     * @param x the x value.
     */
    public void setX(final double x) {
        this.x = x;
        rectangle.setX(x * TILE_SIZE);
    }
    /**
     * A setter for the y value.
     * @param y the y value.
     */
    public void setY(final double y) {
        this.y = y;
        rectangle.setY(y * TILE_SIZE);
    }
    /**
     * A getter for the x value.
     * @return the x value.
     */
    public double getX() {
        return x;
    }
    /**
     * A getter for the y value.
     * @return the y value.
     */
    public double getY() {
        return y;
    }
    /**
     * A getter for the rectangle.
     * @return the rectangle.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    /**
     * A getter for the position.
     * @return the position from the x and y values.
     */
    public Position getPosition() {
        return Position.createNumericPosition((int) x, (int) y);
    }

    @Override
    public String toString() {
        return "GuiPiece :" + getPositionToString();
    }
}
