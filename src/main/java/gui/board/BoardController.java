package gui.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import piece.utils.Position;

/**
 * Controller class for Board.fxml.
 *
 */
public class BoardController {
    @FXML
    private Pane pane;

    private final ChessboardFactory factory = new ChessboardFactoryImpl();
    private final Chessboard board = factory.createNormalCB();

    private final Map<Position, Rectangle> map = new HashMap<>();
    private double lastX;
    private double lastY;
    /**
     * The tile size.
     */
    public static final int TILE_SIZE = 600 / 8;
    /**
     * The width of the board.
     */
    public static final int WIDTH = 8;
    /**
     * The height of the board.
     */
    public static final int HEIGHT = 8;

    @FXML
    void initialize() {
        int count = 0;
        for (int i = 0; i < WIDTH; i++) {
            count++;
            for (int j = 0; j < HEIGHT; j++) {
                final Rectangle r = new Rectangle(i * TILE_SIZE, j * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                map.put(new Position(i, j), r);
                if (count % 2 == 0) {
                    r.setFill(Color.GREEN);
                } else {
                    r.setFill(Color.BEIGE);
                }
                count++;
                r.setStroke(Color.BLACK);
                r.setOnMouseEntered(x -> {
                    r.setStroke(Color.RED);
                    r.setStrokeWidth(4);
                });
                r.setOnMouseExited(x -> {
                    r.setStrokeWidth(1);
                    r.setStroke(Color.BLACK);
                });
                pane.getChildren().add(r);
            }
        }
        final Circle c = new Circle(TILE_SIZE / 2 + TILE_SIZE * 0, TILE_SIZE / 2 + TILE_SIZE * 0, 40);
        lastX = c.getCenterX();
        lastY = c.getCenterY();
        final Image im = new Image("/pieces/images/blackPawn.png");
        c.setFill(new ImagePattern(im));
        c.setOnMouseDragged(x -> dragged(x, c));
        c.setOnMouseReleased(event -> released(c));
        c.setOnMouseClicked(x -> {
            lastX = c.getCenterX();
            lastY = c.getCenterY();
        });
        pane.getChildren().add(c);
    }

    private void dragged(final MouseEvent event, final Circle p) {
        p.setCenterX(event.getX());
        p.setCenterY(event.getY());
    }

    private void released(final Circle p) {
        final int x = (int) p.getCenterX() / TILE_SIZE;
        final int y = (int) p.getCenterY() / TILE_SIZE;
        final Position finalPosition = new Position(x, y);
        if (map.containsKey(finalPosition)) {
            p.setCenterX(TILE_SIZE / 2 + TILE_SIZE * x);
            p.setCenterY(TILE_SIZE / 2 + TILE_SIZE * y);
        } else {
            System.out.println("Posizione errata");
            p.setCenterX(lastX);
            p.setCenterY(lastY);
        }
        System.out.println(finalPosition);
        lightRectangle(finalPosition);
        //return finalPosition;
    }

    private void lightRectangle(final Position finalPosition) {
        if (board.getAllPieces().stream().map(g -> g.getPosition()).collect(Collectors.toList()).contains(finalPosition)) {
            map.get(finalPosition).setFill(Color.BLUE);
        }
    }
}
