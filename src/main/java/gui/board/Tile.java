package gui.board;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import piece.utils.Position;

/**
 * 
 *
 */
public class Tile  extends Rectangle {

    private final Position position;

    Tile(final int x, final int y) {
        setWidth(BoardController.TILE_SIZE);
        setHeight(BoardController.TILE_SIZE);
        this.position = new Position(x, y);
    }

    /**
     * 
     * @return the position
     */
    public Position getPosition() {
        return this.position;
    }

}
