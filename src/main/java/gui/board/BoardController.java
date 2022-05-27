package gui.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
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

    private final ChessboardFactory factory = new ChessboardFactoryImpl();
    private final Chessboard board = factory.createNormalCB();

    private final Map<Position, Rectangle> mapPositionRectangle = new HashMap<>();
    private final Map<GuiPiece, Position> mapGuiPiecePosition = new HashMap<>();
    //probabilmente ti serve una mappa pezzo-rettangolo oppure rettangolo-pezzo (la seconda probably)

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
        this.createGuiPiece();
    }

    private void createGuiPiece() {
        final GuiPiece g = new GuiPiece(TILE_SIZE, TILE_SIZE, "/pieces/images/blackPawn.png");
        final Rectangle r = g.getRectangle();
        lastX = Numbers.FOUR;
        lastY = Numbers.FOUR;
        g.setX(lastX);
        g.setY(lastY);
        mapGuiPiecePosition.put(g, Position.createNumericPosition((int) lastX, (int) lastY));
        r.setOnMouseDragged(x -> dragged(x, r));
        r.setOnMouseReleased(x -> released(g));

        pane.getChildren().add(g.getRectangle());
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

    private void dragged(final MouseEvent event, final Rectangle p) {
        p.setX(event.getX() - (double) TILE_SIZE / 2);
        p.setY(event.getY() - (double) TILE_SIZE / 2);
    }

    private void released(final GuiPiece p) {
        final int x = (int) ((p.getRectangle().getX() + TILE_SIZE / 2) / TILE_SIZE);
        final int y = (int) ((p.getRectangle().getY() + TILE_SIZE / 2) / TILE_SIZE);
        final Position finalPosition = Position.createNumericPosition(x, y);
        if (mapPositionRectangle.containsKey(finalPosition)) {
            lastX = x;
            lastY = y;
            p.setX(lastX);
            p.setY(lastY);
            System.out.println(finalPosition);
            mapGuiPiecePosition.put(p, finalPosition);
        } else {
            System.out.println("Wrong position");
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
