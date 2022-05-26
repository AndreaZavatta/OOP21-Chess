package gui.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import piece.utils.Numbers;
import piece.utils.Position;
import pieces.Piece;

/**
 * Controller class for Board.fxml.
 *
 */
public class BoardController {
    @FXML
    private Pane pane = new Pane();
    @FXML
    private Pane anchorPane = new Pane();

    private final ChessboardFactory factory = new ChessboardFactoryImpl();
    private final Chessboard board = factory.createNormalCB();

    private final Map<Position, Rectangle> mapPositionRectangle = new HashMap<>();
    private final Map<ImageView, Position> mapImagePosition = new HashMap<>();
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
        this.createChessboard();
        this.createPieces();
    }

    private void createPieces() {
        //        final Circle c = new Circle(TILE_SIZE / 2 + TILE_SIZE * 0, TILE_SIZE / 2 + TILE_SIZE * 0, 40);
        //        lastX = c.getCenterX();
        //        lastY = c.getCenterY();
        final Image im = new Image("/pieces/images/blackPawn.png", TILE_SIZE, TILE_SIZE, true, false);
        //        c.setFill(new ImagePattern(im));
        //        c.setOnMouseDragged(x -> dragged(x, c));
        //        c.setOnMouseReleased(event -> released(c));
        //        c.setOnMouseClicked(x -> {
        //            lastX = c.getCenterX();
        //            lastY = c.getCenterY();
        //        });
        final ImageView c = new ImageView(im);
        lastX = TILE_SIZE * Numbers.ONE;
        lastY = TILE_SIZE * Numbers.ZERO;
        c.setX(lastX);
        c.setY(lastY);
        mapImagePosition.put(c, Position.createNumericPosition((int) lastX, (int) lastY));
        //c.setOnMouseDragged(x -> dragged(x, c));
        c.setOnDragDetected(x -> dragged(x, c));
        c.setOnMouseReleased(event -> released(c));
        pane.getChildren().add(c);
    }

    private void createChessboard() {
        int count = 0;
        for (int i = 0; i < WIDTH; i++) {
            count++;
            for (int j = 0; j < HEIGHT; j++) {
                final Rectangle r = new Rectangle(i * TILE_SIZE, j * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                mapPositionRectangle.put(Position.createNumericPosition(i, j), r);
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
    }

    //private void dragged(final DragEvent event, final ImageView p){
    //    p.setX(event.getX());
    //    p.setY(event.getY());
    //}

    private void dragged(final MouseEvent event, final ImageView p) {
        p.setX(event.getX());
        p.setY(event.getY());
    }

    private void released(final ImageView p) {
        final int x = (int) (p.getX() / TILE_SIZE);
        final int y = (int) (p.getY() / TILE_SIZE);
        final Position finalPosition = Position.createNumericPosition(x, y);
        if (mapPositionRectangle.containsKey(finalPosition)) {
            lastX = TILE_SIZE * x;
            lastY = TILE_SIZE * y;
            p.setX(lastX);
            p.setY(lastY);
            System.out.println(finalPosition);
        } else {
            System.out.println("Posizione errata");
            p.setX(lastX);
            p.setY(lastY);
        }
        lightRectangle(finalPosition);
        //return finalPosition;
    }

    private void lightRectangle(final Position finalPosition) {
        if (board.getAllPieces().stream()
                .map(Piece::getPosition).collect(Collectors.toList()).contains(finalPosition)) {
            mapPositionRectangle.get(finalPosition).setFill(Color.BLUE);
        }
    }
}
