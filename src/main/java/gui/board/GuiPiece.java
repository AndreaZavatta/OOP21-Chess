package gui.board;

import javafx.scene.shape.Circle;
/**
 * 
 *
 */
public class GuiPiece extends Circle {

    private double x;
    private double y;
    private final Circle circle;
    /**
     * A standard constructor.
     * 
     * @param x the x value
     * @param y the y value
     * @param circle the circle
     */
    protected GuiPiece(final int x, final int y, final Circle circle) {
        this.circle = circle;
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @return the position
     */
    public String getPosition() {
        return "[x=" + x + ", y=" + y + "]";
    }
    /**
     * 
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
    }
    /**
     * 
     * @param y
     */
    public void setY(final double y) {
        this.y = y;
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
    
    public void draw() {
        circle.setRadius(30);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
    }
}
