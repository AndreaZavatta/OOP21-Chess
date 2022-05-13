package gui.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import piece.utils.Name;
import pieces.Piece;
import piece.utils.Position;

/**
 * Controller class for Board.fxml.
 *
 */
public class BoardController {
    @FXML
    private GridPane pane;

    private final List<Tile> tiles = new ArrayList<>();
    private final Map<Rectangle, Position> rectangle = new HashMap<>();
    private final ChessboardFactory factory = new ChessboardFactoryImpl();
    private final Chessboard board = factory.createNormalCB();
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
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                //                final Tile t = new Tile(i, j);
                //                tiles.add(t);
                //                t.setOnMousePressed(e -> printPos(t));
                //                t.setStroke(Color.BLACK);
                //                t.setFill(Color.RED);
                //                pane.add(t, i, j);
                final Rectangle r = new Rectangle(TILE_SIZE, TILE_SIZE);
                r.setStroke(Color.BLACK);
                r.setFill(Color.BLUE);
                rectangle.put(r, new Position(i, j));
                r.setOnMousePressed(e -> printPos(r));
                pane.add(r, i, j);
            }
        }
        this.updateView();
    }

    private void updateView() {
        final List<Piece> l =  board.getAllPieces();
        rectangle.forEach((r, pos) -> {
            if (l.stream().map(y -> y.getPosition()).collect(Collectors.toList()).contains(pos)) {
                final Piece p = l.stream().filter(a -> a.getPosition().equals(pos)).findFirst().get();
                if (p.getName().equals(Name.PAWN)) {
                    final Image im = new Image("/pieces/images/blackPawn.png");
                    final GuiPiece c = new GuiPiece(p.getPosition().getX(), p.getPosition().getY(), new Circle());
                    c.setFill(new ImagePattern(im));
                    //c.setStroke(Color.BLACK);
                    c.prefHeight(10);
                    c.prefWidth(10);
                    c.setOnMousePressed(event -> pressed(event, c));
                    c.setOnMouseDragged(event -> dragged(event, c));
                    c.setOnMouseReleased(event -> released(event, c));
                    c.setRadius(10);
                    pane.add(c, pos.getX(), pos.getY());
                }
            }
        });
    }

    private void printPos(final Rectangle r) {
        System.out.println(rectangle.get(r).toString());
        r.setFill(Color.BEIGE);
    }

    private void pressed(final MouseEvent event, final GuiPiece c) {
        c.setFill(Color.DARKGOLDENROD);
        System.out.println(c.getPosition());
    }

    private void dragged(final MouseEvent event, final GuiPiece c) {
        c.setX(c.getX() + event.getX());
        c.setX(c.getY() + event.getY());
        c.draw();
    }

    private void released(final MouseEvent event, final GuiPiece p) {
        final int gridx = (int) p.getX() / TILE_SIZE;
        final int gridy = (int) p.getY() / TILE_SIZE;
        p.setX(TILE_SIZE / 2 + TILE_SIZE * gridx);
        p.setY(TILE_SIZE / 2 + TILE_SIZE * gridy);
        p.draw();
    }
}
