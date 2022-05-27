package gui.board;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static gui.board.BoardController.TILE_SIZE;

/**
 * 
 *
 */
public class GuiPiece {
    private double x;
    private double y;
    private final Rectangle rectangle;
    /**
     * A standard constructor.
     *
     * @param s the rectangle
     * @param x the x value
     * @param y the y value
     */
    protected GuiPiece(final double x, final double y, final String s) {
        this.rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
        final Image im = new Image(s, TILE_SIZE, TILE_SIZE, true, false);
        rectangle.setFill(new ImagePattern(im));
        this.x = x;
        this.y = y;
        this.rectangle.setOnMouseClicked(a -> {
            System.out.println(getPosition());
        });
    }
    /**
     * 
     * @return the position
     */
    public String getPosition() {
        return "[x=" + (int) x + ", y=" + (int) y + "]";
    }
    /**
     *
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
        rectangle.setX(x * TILE_SIZE);
    }
    /**
     *
     * @param y
     */
    public void setY(final double y) {
        this.y = y;
        rectangle.setY(y * TILE_SIZE);
    }
    /**
     *
     * @return the x value
     */
    public double getX() {
        return x;
    }
    /**
     *
     * @return the y value
     */
    public double getY() {
        return y;
    }
    /**
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
