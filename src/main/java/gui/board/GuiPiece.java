package gui.board;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
     * @param c the circle
     */
    protected GuiPiece(final int x, final int y, final Circle c) {
        this.circle = c;
        this.x = x;
        this.y = y;
        this.prefHeight(10);
        this.prefWidth(10);
        this.setOnMousePressed(event -> pressed(this));
        this.setOnMouseDragged(event -> dragged(event, this));
        this.setOnMouseReleased(event -> released(this));
        this.setRadius(10);
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
    /**
     * 
     */
    public void draw() {
        circle.setRadius(30);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
    }

    private void pressed(final GuiPiece c) {
        //c.setFill(Color.DARKGOLDENROD);
        System.out.println(c.getPosition());
    }

    private void dragged(final MouseEvent event, final GuiPiece c) {
        c.setX(c.getX() + event.getX());
        c.setX(c.getY() + event.getY());
        //c.draw();
    }

    private void released(final GuiPiece p) {
        final int gridx = (int) p.getX() / BoardController.TILE_SIZE;
        final int gridy = (int) p.getY() / BoardController.TILE_SIZE;
        p.setX(BoardController.TILE_SIZE / 2 + BoardController.TILE_SIZE * gridx);
        p.setY(BoardController.TILE_SIZE / 2 + BoardController.TILE_SIZE * gridy);
        //p.draw();
    }
}
